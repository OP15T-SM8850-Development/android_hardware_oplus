package com.oplus.util;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Stub for com.oplus.util.OplusDarkModeUtil.
 *
 * Hard-referenced by OppoGallery2 (only isNightMode(Context) is referenced).
 * isNightMode is a pure device-state check (uiMode night mask) with no OEM
 * dependencies, so it is ported faithfully from the OOS oplus-framework.jar.
 */
public class OplusDarkModeUtil {

    public static boolean isNightMode(Context context) {
        if (context == null) {
            return false;
        }
        Configuration configuration = context.getResources().getConfiguration();
        int currentNightMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES;
    }
}
