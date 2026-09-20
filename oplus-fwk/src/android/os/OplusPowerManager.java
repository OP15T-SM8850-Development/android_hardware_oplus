package android.os;

import android.os.Bundle;

/**
 * Class-closure stub for the OEM oplus-framework android.os.OplusPowerManager.
 * Referenced by OplusCamera: const-class + new-instance + invoke-direct <init>()V.
 * Real OOS kind: public class extends Object. Public constant fields and the
 * AOSP/primitive-typed public API are ported with safe no-op/default returns.
 * OMITTED (would drag in out-of-scope oplus companion types, and are NOT
 * referenced by OplusCamera):
 *   - getService():android.os.IOplusPowerManager
 *   - registerScreenStatusListener(com.oplus.os.IOplusScreenStatusListener)
 *   - unregisterScreenStatusListener(com.oplus.os.IOplusScreenStatusListener)
 * reboot()/shutdown() are intentionally no-ops.
 */
public class OplusPowerManager {

    public static final int WAKE_UP_REASON_FINGERPRINT = 0x62;
    public static final int GO_TO_SLEEP_REASON_FINGERPRINT = 0x65;
    public static final String WAKE_DATA_TIMES = "times";

    public OplusPowerManager() {
    }

    public void disableScreenStayAwakeOfApp(boolean enable, int uid) {
    }

    public double getAppPowerConsumed(int uid) {
        return 0.0d;
    }

    public int getDefaultBrightness() {
        return 0;
    }

    public int getDefaultScreenBrightnessSetting() {
        return 0;
    }

    public boolean getDisplayAodStatus() {
        return false;
    }

    public float[] getDisplaysBrightnessByNit(float nit) {
        return new float[0];
    }

    public int getLastSleepReason() {
        return 0;
    }

    public int getMaxBrightness() {
        return 0;
    }

    public int getMaximumScreenBrightnessSetting() {
        return 0;
    }

    public int getMinBrightness() {
        return 0;
    }

    public long getMinScreenOffTimeout() {
        return 0L;
    }

    public int getMinimumScreenBrightnessSetting() {
        return 0;
    }

    public int getTimerCount(int type, int subType) {
        return 0;
    }

    public void goToSleep(long time) {
    }

    public boolean isScreenStayAwake() {
        return false;
    }

    public void reboot(String reason) {
    }

    public void setFlashing(int a, int b, int c, int d, int e) {
    }

    public boolean setMinScreenOffTimeout(long timeout) {
        return false;
    }

    public void setPocketWhenProx(boolean enable) {
    }

    public boolean setPowerSaveModeEnabled(boolean enabled) {
        return false;
    }

    public void shutdown(boolean confirm, String reason, boolean wait) {
    }

    public void userActivity(long time, int event, int flags) {
    }

    public void wakeUp(int reason, String details, long time, Bundle extras) {
    }
}
