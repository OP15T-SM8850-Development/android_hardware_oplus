/*
 * OEM App-Lock / access-control CLIENT (oplus-fwk boot jar).
 *
 * Phase-1 routing: the gallery-facing methods now query the real oplus_accesscontrol service
 * (OplusAccessControlManagerService) instead of returning empty maps, so the OnePlus gallery's
 * in-app Hidden/Locked album reflects real hide/reveal state.
 *
 * isEncryptPass/isEncryptedPackage are deliberately LEFT as the device-validated both-true stub
 * (they drive the camera thumbnail-jump path, validated in v3.3; the gallery does not consume them).
 * All routed calls fail-safe to the prior empty/false defaults if the service is unavailable.
 */
package com.oplus.app;

import android.os.ServiceManager;
import android.os.UserHandle;

import java.util.HashMap;
import java.util.Map;

public class OPlusAccessControlManager {
    private static final String SERVICE = "oplus_accesscontrol";
    private static final String TYPE_HIDE = "type_hide";
    private static final String TYPE_ENCRYPT = "type_encrypt";

    private static volatile OPlusAccessControlManager sInstance = null;
    public static final int USER_CURRENT = UserHandle.myUserId();

    private OPlusAccessControlManager() {
    }

    public static OPlusAccessControlManager getInstance() {
        if (sInstance == null) {
            synchronized (OPlusAccessControlManager.class) {
                if (sInstance == null) {
                    sInstance = new OPlusAccessControlManager();
                }
            }
        }
        return sInstance;
    }

    private static IOplusAccessControlManager service() {
        // asInterface(null) returns null, so this is null-safe if the service isn't registered yet.
        return IOplusAccessControlManager.Stub.asInterface(ServiceManager.getService(SERVICE));
    }

    // --- camera thumbnail-jump path: keep the v3.3 device-validated behavior (not gallery-relevant) ---
    public boolean isEncryptPass(String packageName, int userId) {
        return true;
    }

    public boolean isEncryptedPackage(String packageName, int userId) {
        return true;
    }

    // --- gallery hidden/locked-album gate: route to the real service ---
    public Map getAccessControlAppsInfo(String type, int userId) {
        try {
            IOplusAccessControlManager s = service();
            if (s != null) {
                Map m = s.getAccessControlAppsInfo(type, userId);
                if (m != null) return m;
            }
        } catch (Exception e) {
            // fail-safe below
        }
        return new HashMap();
    }

    public boolean getAccessControlEnabled(String type, int userId) {
        try {
            IOplusAccessControlManager s = service();
            if (s != null) return s.getAccessControlEnabled(type, userId);
        } catch (Exception e) {
            // fail-safe below
        }
        return false;
    }

    public boolean getApplicationAccessControlEnabledAsUser(String pkg, int userId) {
        try {
            IOplusAccessControlManager s = service();
            if (s != null) {
                Map hide = s.getAccessControlAppsInfo(TYPE_HIDE, userId);
                Map enc = s.getAccessControlAppsInfo(TYPE_ENCRYPT, userId);
                return (hide != null && hide.containsKey(pkg))
                        || (enc != null && enc.containsKey(pkg));
            }
        } catch (Exception e) {
            // fail-safe below
        }
        return false;
    }

    public Map getPrivacyAppInfo(int userId) {
        try {
            IOplusAccessControlManager s = service();
            if (s != null) {
                Map m = s.getAccessControlAppsInfo(TYPE_HIDE, userId);
                if (m != null) return m;
            }
        } catch (Exception e) {
            // fail-safe below
        }
        return new HashMap();
    }

    public boolean registerAccessControlObserver(String type, com.oplus.app.IOplusAccessControlObserver observer) {
        try {
            IOplusAccessControlManager s = service();
            if (s != null) return s.registerAccessControlObserver(type, observer);
        } catch (Exception e) {
            // fail-safe below
        }
        return true;
    }

    public boolean unregisterAccessControlObserver(String type, com.oplus.app.IOplusAccessControlObserver observer) {
        try {
            IOplusAccessControlManager s = service();
            if (s != null) return s.unregisterAccessControlObserver(type, observer);
        } catch (Exception e) {
            // fail-safe below
        }
        return true;
    }
}
