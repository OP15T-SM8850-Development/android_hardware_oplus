package com.itgsa.opensdk.wm;

/**
 * Stub for OEM com.itgsa.opensdk.wm.ActivityMultiWindowAllowanceObserver.
 * OEM version wraps an IActivityMultiWindowAllowanceObserver AIDL stub; the consumer
 * (OppoGallery2) only references the public no-arg constructor and may override the
 * onMultiWindowAllowanceChanged callback. Our MultiWindowTrigger stub never invokes the
 * callback, so no oplus.* companion is pulled in.
 */
public class ActivityMultiWindowAllowanceObserver {
    public ActivityMultiWindowAllowanceObserver() {
    }

    public void onMultiWindowAllowanceChanged(ActivityMultiWindowAllowance allowance) {
    }
}
