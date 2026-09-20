package android.os.customize;

import android.content.Context;

/**
 * Closure stub for OEM android.os.customize.OplusCustomizeContactManager.
 * Enterprise MDM contact-restriction manager referenced by OppoGallery2.
 * Safe permissive defaults: no blacklist / no forbidden call-log / pattern 0.
 */
public class OplusCustomizeContactManager {
    private static volatile OplusCustomizeContactManager sInstance;

    private OplusCustomizeContactManager() {
    }

    public static final OplusCustomizeContactManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (OplusCustomizeContactManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusCustomizeContactManager();
                }
            }
        }
        return sInstance;
    }

    public int getContactBlockPattern() {
        return 0;
    }

    public int getContactMatchPattern() {
        return 0;
    }

    public int getContactNumberHideMode() {
        return 0;
    }

    public int getContactNumberMaskEnable() {
        return 0;
    }

    public int getContactOutgoOrIncomePattern() {
        return 0;
    }

    public boolean isContactBlackListEnable() {
        return false;
    }

    public boolean isForbidCallLogEnable() {
        return false;
    }
}
