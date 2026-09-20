package com.oplus.datasync;

import android.os.Bundle;

/**
 * Stub for the OEM com.oplus.datasync.OplusDataSyncManager.
 *
 * Referenced (hard-loaded) by OppoGallery2:
 *   static getInstance()Lcom/oplus/datasync/OplusDataSyncManager;
 *   updateAppData(Ljava/lang/String;Landroid/os/Bundle;)Z
 *
 * OOS surface: public class extends Object, private singleton via volatile
 * sInstance, private ctor. updateAppData is a data-sync no-op that returns a
 * success boolean; safe stub default is false (no-op, non-crashing).
 */
public class OplusDataSyncManager {

    // Public constant present on the OEM class (kept for API fidelity).
    public static final String MODULE_INTERCEPT_SCREEN_WINDOW = "module_intercept_screen_window";

    private static volatile OplusDataSyncManager sInstance;

    private OplusDataSyncManager() {
    }

    public static OplusDataSyncManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusDataSyncManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusDataSyncManager();
                }
            }
        }
        return sInstance;
    }

    public boolean updateAppData(String module, Bundle bundle) {
        return false;
    }
}
