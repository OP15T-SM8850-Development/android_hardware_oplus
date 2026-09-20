package com.oplus.ota;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Stub of com.oplus.ota.OplusRecoverySystem.
 *
 * Referenced by OppoGallery2 via the com.heytap.addon.ota.OplusRecoverySystem
 * wrapper, which delegates:
 *   invoke-static Lcom/oplus/ota/OplusRecoverySystem;->
 *       installOplusOtaPackage(Landroid/content/Context;Ljava/util/ArrayList;)V
 *
 * Real OOS class kind: public class extends java.lang.Object (not an
 * interface/enum). The method is a static OTA-install entry point (not a
 * feature query), so the safe, non-crashing default is a no-op.
 */
public class OplusRecoverySystem {
    private static final String TAG = "OplusRecoverySystem";

    public OplusRecoverySystem() {
    }

    // OOS signature: public static void installOplusOtaPackage(
    //     Context, ArrayList<File>) throws IOException
    // Descriptor referenced by consumer: (Landroid/content/Context;Ljava/util/ArrayList;)V
    public static void installOplusOtaPackage(Context context, ArrayList<File> packageFileList)
            throws IOException {
        Log.d(TAG, "installOplusOtaPackage(stub): no-op");
    }
}
