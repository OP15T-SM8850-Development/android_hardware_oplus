package android.os.customize;

import android.content.Context;

/**
 * Closure stub for OEM android.os.customize.OplusCustomizeStateManager.
 * Enterprise MDM device-state manager referenced by OplusCamera.
 * Only getInstance is hard-referenced; return a non-null singleton to avoid NPE.
 */
public class OplusCustomizeStateManager {
    private static volatile OplusCustomizeStateManager sInstance;

    private OplusCustomizeStateManager() {
    }

    public static final OplusCustomizeStateManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (OplusCustomizeStateManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusCustomizeStateManager();
                }
            }
        }
        return sInstance;
    }
}
