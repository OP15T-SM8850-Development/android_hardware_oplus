package com.oplus.server;

import android.Manifest;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Slog;

import com.oplus.app.IOplusAccessControlManager;
import com.oplus.app.IOplusAccessControlObserver;
import com.oplus.app.OplusAccessControlInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class OplusAccessControlManagerService extends IOplusAccessControlManager.Stub {
    private static final String TAG = "OplusAccessControlManagerService";

    private static final String TYPE_ENCRYPT = "type_encrypt";
    private static final String TYPE_HIDE = "type_hide";

    private final Context mContext;
    private final OplusAccessControlStateStore mStateStore;
    private final RemoteCallbackList<IOplusAccessControlObserver> mObservers =
            new RemoteCallbackList<>();

    public OplusAccessControlManagerService(Context context) {
        mContext = context;
        mStateStore = new OplusAccessControlStateStore(context);
    }

    public void onSystemReady() {
        final Uri encryptUri = mStateStore.getEnabledUri(TYPE_ENCRYPT);
        final Uri hideUri = mStateStore.getEnabledUri(TYPE_HIDE);
        mContext.getContentResolver().registerContentObserver(encryptUri, false,
                new SettingsObserver(new Handler()));
        mContext.getContentResolver().registerContentObserver(hideUri, false,
                new SettingsObserver(new Handler()));
        Slog.i(TAG, "OplusAccessControlManagerService ready");
    }

    @Override
    public void setAccessControlAppsInfo(String type, Map accessControlInfo, int userId) {
        enforceManageAccessControl();
        final String normalizedType = normalizeType(type);
        if (normalizedType == null) {
            Slog.w(TAG, "setAccessControlAppsInfo type mismatch: " + type);
            return;
        }
        final HashMap<String, Integer> apps = sanitizeMap(accessControlInfo);
        mStateStore.setApps(normalizedType, userId, apps);
        notifyAccessControlStateChanged(normalizedType, apps, userId);
    }

    @Override
    public Map getAccessControlAppsInfo(String type, int userId) {
        final String normalizedType = normalizeType(type);
        if (normalizedType == null) {
            Slog.w(TAG, "getAccessControlAppsInfo type mismatch: " + type);
            return new HashMap<String, Integer>();
        }
        return mStateStore.getApps(normalizedType, userId);
    }

    @Override
    public void setAccessControlEnabled(String type, boolean enable, int userId) {
        enforceManageAccessControl();
        final String normalizedType = normalizeType(type);
        if (normalizedType == null) {
            Slog.w(TAG, "setAccessControlEnabled type mismatch: " + type);
            return;
        }
        mStateStore.setEnabled(normalizedType, enable, userId);
        notifyAccessControlEnableChanged(normalizedType, enable, userId);
    }

    @Override
    public boolean getAccessControlEnabled(String type, int userId) {
        final String normalizedType = normalizeType(type);
        if (normalizedType == null) {
            Slog.w(TAG, "getAccessControlEnabled type mismatch: " + type);
            return false;
        }
        return mStateStore.getEnabled(normalizedType, userId);
    }

    @Override
    public void addEncryptPass(String packageName, int windowMode, int userId) {
        enforceManageAccessControl();
        if (packageName == null || packageName.isEmpty()) {
            return;
        }
        mStateStore.addEncryptPass(packageName, userId);
    }

    @Override
    public boolean isEncryptPass(String packageName, int userId) {
        if (packageName == null) {
            return false;
        }
        // Auth is trivially "passed" when the package is not in the encrypt set
        // (nothing gated); gate for real only when the package IS encrypt-gated.
        // Reproduces the device-validated both-true behavior for the nothing-locked
        // state while preserving real gating once a package is added to the encrypt set.
        return !isEncryptedPackage(packageName, userId)
                || mStateStore.isEncryptPass(packageName, userId);
    }

    @Override
    public boolean isEncryptedPackage(String packageName, int userId) {
        if (packageName == null) {
            return false;
        }
        return mStateStore.isPackageEnabled(TYPE_ENCRYPT, packageName, userId);
    }

    @Override
    public boolean registerAccessControlObserver(String type, IOplusAccessControlObserver observer) {
        enforceManageAccessControl();
        final String normalizedType = normalizeType(type);
        if (normalizedType == null || observer == null) {
            return false;
        }
        return mObservers.register(observer, normalizedType);
    }

    @Override
    public boolean unregisterAccessControlObserver(String type, IOplusAccessControlObserver observer) {
        enforceManageAccessControl();
        if (observer == null) {
            return false;
        }
        return mObservers.unregister(observer);
    }

    @Override
    public void updateRusList(int type, List<String> addList, List<String> deleteList) {
        enforceManageAccessControl();
        Slog.d(TAG, "updateRusList type=" + type);
    }

    private void enforceManageAccessControl() {
        mContext.enforceCallingOrSelfPermission(Manifest.permission.WRITE_SECURE_SETTINGS,
                "Oplus access-control management requires WRITE_SECURE_SETTINGS");
    }

    private void notifyAccessControlStateChanged(String type, Map<String, Integer> apps, int userId) {
        final int count = mObservers.beginBroadcast();
        try {
            for (int i = 0; i < count; i++) {
                if (!type.equals(mObservers.getBroadcastCookie(i))) {
                    continue;
                }
                for (Map.Entry<String, Integer> entry : apps.entrySet()) {
                    final OplusAccessControlInfo info = new OplusAccessControlInfo();
                    info.mName = entry.getKey();
                    info.userId = userId;
                    info.isEncrypted = TYPE_ENCRYPT.equals(type) && entry.getValue() != 0;
                    info.isHideIcon = TYPE_HIDE.equals(type) && entry.getValue() != 0;
                    try {
                        if (TYPE_HIDE.equals(type)) {
                            mObservers.getBroadcastItem(i).onHideStateChange(info);
                        } else {
                            mObservers.getBroadcastItem(i).onEncryptStateChange(info);
                        }
                    } catch (RemoteException ignored) {
                    }
                }
            }
        } finally {
            mObservers.finishBroadcast();
        }
    }

    private void notifyAccessControlEnableChanged(String type, boolean enabled, int userId) {
        final int count = mObservers.beginBroadcast();
        try {
            for (int i = 0; i < count; i++) {
                if (!type.equals(mObservers.getBroadcastCookie(i))) {
                    continue;
                }
                final OplusAccessControlInfo info = new OplusAccessControlInfo();
                info.userId = userId;
                info.isEncrypted = TYPE_ENCRYPT.equals(type) && enabled;
                info.isHideIcon = TYPE_HIDE.equals(type) && enabled;
                try {
                    if (TYPE_HIDE.equals(type)) {
                        mObservers.getBroadcastItem(i).onHideEnableChange(enabled);
                    } else {
                        mObservers.getBroadcastItem(i).onEncryptEnableChange(enabled);
                    }
                } catch (RemoteException ignored) {
                }
            }
        } finally {
            mObservers.finishBroadcast();
        }
    }

    private static String normalizeType(String type) {
        if (TYPE_ENCRYPT.equals(type) || "type_encrypt_ignore_enable".equals(type)) {
            return TYPE_ENCRYPT;
        }
        if (TYPE_HIDE.equals(type) || "type_hide_ignore_enable".equals(type)) {
            return TYPE_HIDE;
        }
        return null;
    }

    private static HashMap<String, Integer> sanitizeMap(Map accessControlInfo) {
        final HashMap<String, Integer> result = new HashMap<>();
        if (accessControlInfo == null) {
            return result;
        }
        for (Object item : accessControlInfo.entrySet()) {
            if (!(item instanceof Map.Entry)) {
                continue;
            }
            final Map.Entry entry = (Map.Entry) item;
            if (entry.getKey() instanceof String && entry.getValue() instanceof Integer) {
                result.put((String) entry.getKey(), (Integer) entry.getValue());
            }
        }
        return result;
    }

    private final class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            mStateStore.clear();
        }
    }
}
