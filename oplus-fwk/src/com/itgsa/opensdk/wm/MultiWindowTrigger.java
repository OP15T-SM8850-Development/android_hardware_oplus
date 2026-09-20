package com.itgsa.opensdk.wm;

import android.app.Activity;
import android.content.Context;

/**
 * Stub for OEM com.itgsa.opensdk.wm.MultiWindowTrigger.
 * OEM body delegates to com.oplus.splitscreen.OplusSplitScreenManager / com.oplus.app.*;
 * we replace it with no-ops so OppoGallery2 links without those companions. Split-screen
 * multi-window is unrelated to the camera/livephoto/DV goals, so isDeviceSupport reports
 * false and the gallery cleanly skips the whole multi-window path (safe default).
 */
public class MultiWindowTrigger {
    private static final int SDK_VERSION = 10000;

    public MultiWindowTrigger() {
    }

    public int getVersion() {
        return SDK_VERSION;
    }

    public boolean isDeviceSupport(Context context) {
        return false;
    }

    public void registerActivityMultiWindowAllowanceObserver(Activity activity,
            ActivityMultiWindowAllowanceObserver observer) {
    }

    public boolean requestSwitchToFullScreen(Activity activity) {
        return false;
    }

    public boolean requestSwitchToSplitScreen(Activity requestActivity, SplitScreenParams params) {
        return false;
    }

    public void unregisterActivityMultiWindowAllowanceObserver(Activity activity,
            ActivityMultiWindowAllowanceObserver observer) {
    }
}
