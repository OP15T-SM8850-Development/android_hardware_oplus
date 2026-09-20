package android.os.oplusdevicepolicy;

/**
 * Class-closure stub for the OEM OplusDevicepolicyManager (MDM/enterprise
 * device-policy accessor), ported to satisfy hard class-load + member
 * references from the shipped OppoGallery2.apk.
 *
 * Consumer-referenced surface (OppoGallery2, classes10.dex) -- complete set:
 *   - public static final getInstance()Landroid/os/oplusdevicepolicy/OplusDevicepolicyManager;
 *   - public getBoolean(Ljava/lang/String;IZ)Z
 *
 * Semantics ported faithfully from OOS oplus-framework.jar. No
 * "oplusdevicepolicy" binder service exists in this port, so getBoolean
 * returns the caller-supplied default -- this is the OEM's own fallback path
 * (getData() -> null -> return defaultvalue). Non-crashing and permissive
 * (an absent MDM policy imposes no restriction).
 */
public class OplusDevicepolicyManager {
    public static final String SERVICE_NAME = "oplusdevicepolicy";
    public static final int SYSTEM_DATA_TYPE = 0;
    public static final int CUSTOMIZE_DATA_TYPE = 1;

    private static volatile OplusDevicepolicyManager sInstance;

    private OplusDevicepolicyManager() {
    }

    public static final OplusDevicepolicyManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusDevicepolicyManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusDevicepolicyManager();
                }
            }
        }
        return sInstance;
    }

    public boolean getBoolean(String name, int datatype, boolean defaultvalue) {
        // No device-policy service present: return the caller default,
        // matching the OEM fallback when no MDM policy data is available.
        return defaultvalue;
    }
}
