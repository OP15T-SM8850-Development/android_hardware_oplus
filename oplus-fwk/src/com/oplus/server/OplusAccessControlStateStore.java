package com.oplus.server;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.util.ArrayMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class OplusAccessControlStateStore {
    private static final String TYPE_ENCRYPT = "type_encrypt";
    private static final String TYPE_HIDE = "type_hide";
    private static final String KEY_ENABLED_PREFIX = "oplus_access_control_enabled_";
    private static final String KEY_APPS_PREFIX = "oplus_access_control_apps_";
    private static final String KEY_PASS_PREFIX = "oplus_access_control_pass_";

    private final Context mContext;
    private final Object mLock = new Object();
    private final ArrayMap<Integer, UserState> mUserStates = new ArrayMap<>();

    OplusAccessControlStateStore(Context context) {
        mContext = context;
    }

    Uri getEnabledUri(String type) {
        return Settings.Secure.getUriFor(KEY_ENABLED_PREFIX + type);
    }

    void clear() {
        synchronized (mLock) {
            mUserStates.clear();
        }
    }

    HashMap<String, Integer> getApps(String type, int userId) {
        synchronized (mLock) {
            final HashMap<String, Integer> apps = getUserStateLocked(userId).apps.get(type);
            return apps == null ? new HashMap<String, Integer>() : new HashMap<>(apps);
        }
    }

    void setApps(String type, int userId, HashMap<String, Integer> apps) {
        synchronized (mLock) {
            getUserStateLocked(userId).apps.put(type, apps);
            persistAppsLocked(type, userId, apps);
        }
    }

    boolean getEnabled(String type, int userId) {
        synchronized (mLock) {
            final Boolean enabled = getUserStateLocked(userId).enabled.get(type);
            return enabled != null && enabled;
        }
    }

    void setEnabled(String type, boolean enable, int userId) {
        synchronized (mLock) {
            getUserStateLocked(userId).enabled.put(type, enable);
            Settings.Secure.putIntForUser(mContext.getContentResolver(),
                    KEY_ENABLED_PREFIX + type, enable ? 1 : 0, userId);
        }
    }

    void addEncryptPass(String packageName, int userId) {
        synchronized (mLock) {
            final UserState state = getUserStateLocked(userId);
            state.encryptPassPackages.add(packageName);
            persistPassPackagesLocked(userId, state.encryptPassPackages);
        }
    }

    boolean isEncryptPass(String packageName, int userId) {
        synchronized (mLock) {
            return getUserStateLocked(userId).encryptPassPackages.contains(packageName);
        }
    }

    boolean isPackageEnabled(String type, String packageName, int userId) {
        synchronized (mLock) {
            final Integer value = getUserStateLocked(userId).apps.get(type).get(packageName);
            return value != null && value != 0;
        }
    }

    private UserState getUserStateLocked(int userId) {
        UserState state = mUserStates.get(userId);
        if (state == null) {
            state = new UserState();
            state.enabled.put(TYPE_ENCRYPT, Settings.Secure.getIntForUser(
                    mContext.getContentResolver(), KEY_ENABLED_PREFIX + TYPE_ENCRYPT, 0, userId) != 0);
            state.enabled.put(TYPE_HIDE, Settings.Secure.getIntForUser(
                    mContext.getContentResolver(), KEY_ENABLED_PREFIX + TYPE_HIDE, 0, userId) != 0);
            state.apps.put(TYPE_ENCRYPT, readApps(TYPE_ENCRYPT, userId));
            state.apps.put(TYPE_HIDE, readApps(TYPE_HIDE, userId));
            state.encryptPassPackages.addAll(readStringSet(KEY_PASS_PREFIX, userId));
            mUserStates.put(userId, state);
        }
        return state;
    }

    private HashMap<String, Integer> readApps(String type, int userId) {
        final HashMap<String, Integer> result = new HashMap<>();
        final String raw = Settings.Secure.getStringForUser(
                mContext.getContentResolver(), KEY_APPS_PREFIX + type, userId);
        if (raw == null || raw.isEmpty()) {
            return result;
        }
        for (String entry : raw.split(";")) {
            final int split = entry.lastIndexOf('=');
            if (split <= 0 || split == entry.length() - 1) {
                continue;
            }
            try {
                result.put(entry.substring(0, split), Integer.parseInt(entry.substring(split + 1)));
            } catch (NumberFormatException ignored) {
            }
        }
        return result;
    }

    private Set<String> readStringSet(String prefix, int userId) {
        final HashSet<String> result = new HashSet<>();
        final String raw = Settings.Secure.getStringForUser(
                mContext.getContentResolver(), prefix + userId, userId);
        if (raw == null || raw.isEmpty()) {
            return result;
        }
        for (String entry : raw.split(";")) {
            if (!entry.isEmpty()) {
                result.add(entry);
            }
        }
        return result;
    }

    private void persistAppsLocked(String type, int userId, Map<String, Integer> apps) {
        final StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : apps.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                continue;
            }
            if (builder.length() > 0) {
                builder.append(';');
            }
            builder.append(entry.getKey()).append('=').append(entry.getValue());
        }
        Settings.Secure.putStringForUser(mContext.getContentResolver(),
                KEY_APPS_PREFIX + type, builder.toString(), userId);
    }

    private void persistPassPackagesLocked(int userId, Set<String> packages) {
        final StringBuilder builder = new StringBuilder();
        for (String packageName : packages) {
            if (builder.length() > 0) {
                builder.append(';');
            }
            builder.append(packageName);
        }
        Settings.Secure.putStringForUser(mContext.getContentResolver(),
                KEY_PASS_PREFIX + userId, builder.toString(), userId);
    }

    private static final class UserState {
        final ArrayMap<String, Boolean> enabled = new ArrayMap<>();
        final ArrayMap<String, HashMap<String, Integer>> apps = new ArrayMap<>();
        final Set<String> encryptPassPackages = new HashSet<>();
    }
}
