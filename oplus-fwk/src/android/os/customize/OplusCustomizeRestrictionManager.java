package android.os.customize;

import android.content.ComponentName;
import android.content.Context;

/**
 * Closure stub for OEM android.os.customize.OplusCustomizeRestrictionManager.
 * Enterprise MDM restriction manager referenced by OplusCamera and OppoGallery2.
 * Safe permissive defaults: nothing disabled/forbidden (restriction queries -> false).
 */
public class OplusCustomizeRestrictionManager {
    private static volatile OplusCustomizeRestrictionManager sInstance;

    private OplusCustomizeRestrictionManager(Context context) {
    }

    public static final OplusCustomizeRestrictionManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (OplusCustomizeRestrictionManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusCustomizeRestrictionManager(context);
                }
            }
        }
        return sInstance;
    }

    public boolean getForbidRecordScreenState() {
        return false;
    }

    public boolean isFloatTaskDisabled(ComponentName componentName) {
        return false;
    }

    public boolean isPrivateSafeDisabled() {
        return false;
    }

    public boolean setFloatTaskDisabled(ComponentName componentName, boolean disabled) {
        return false;
    }
}
