package com.itgsa.opensdk.wm;

/**
 * Stub for OEM com.itgsa.opensdk.wm.ActivityMultiWindowAllowance.
 * Plain data holder; public boolean fields read by OppoGallery2. Faithful to OOS.
 */
public class ActivityMultiWindowAllowance {
    public boolean allowSelfSplitToSplitScreen;
    public boolean allowSwitchToFullScreen;
    public boolean allowSwitchToSplitScreen;

    public ActivityMultiWindowAllowance(boolean allowSelfSplitToSplitScreen,
                                        boolean allowSwitchToSplitScreen,
                                        boolean allowSwitchToFullScreen) {
        this.allowSelfSplitToSplitScreen = allowSelfSplitToSplitScreen;
        this.allowSwitchToSplitScreen = allowSwitchToSplitScreen;
        this.allowSwitchToFullScreen = allowSwitchToFullScreen;
    }
}
