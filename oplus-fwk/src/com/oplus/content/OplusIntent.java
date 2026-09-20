package com.oplus.content;

import android.content.Intent;

/**
 * Stub of the OEM com.oplus.content.OplusIntent.
 * Constants ported byte-faithfully from OOS oplus-framework.jar (OEM typos such
 * as OPLUS_FALG_* / OPLUS_FLAG_MUTIL_* / OPULS_FLAG_* preserved so consumer sget
 * refs resolve). The real static getters/setters route through
 * Intent.getIntentExt() -> IIntentExt, an OEM hook that does not exist in our
 * framework; they are therefore safe no-ops (return 0 / void) per the closure
 * oracle. Consumers only hard-reference setOplusFlags(Intent,int).
 */
public class OplusIntent {

    public static final String ACTION_CAMERA_MODE_CHANGE = "android.intent.action.CAMERA_MODE_CHANGE";
    public static final String ACTION_CLOSE_NOTIFICATION_DIALOG = "android.intent.action.CLOSE_NOTIFICATION_DIALOG";
    public static final String ACTION_DATA_COLLECT_CLEAR = "oplus.intent.action.DATA_COLLECT_CLEAR";
    public static final String ACTION_DATA_DEFAULT_SIM_CHANGED = "android.intent.action.DATA_DEFAULT_SIM";
    public static final String ACTION_DUAL_SIM_MODE_CHANGED = "android.intent.action.DUAL_SIM_MODE";
    public static final String ACTION_FILE_ENCRYPT_DECRYPT = "oplus.intent.action.decrypt";
    public static final String ACTION_FILE_ENCRYPT_ENCRYPT = "oplus.intent.action.encrypt";
    public static final String ACTION_FILE_ENCRYPT_STATE_CHANGED = "oplus.intent.action.encrypt.stateChanged";
    public static final String ACTION_HOME_MODE_CHANGE = "android.intent.action.HOME_MODE_CHANGE";
    public static final String ACTION_LID_STATE_CHANGED = "com.oplus.intent.action.LID_STATE_CHANGED";
    public static final String ACTION_MEDIA_SCANNER_SCAN_ALL = "oplus.intent.action.MEDIA_SCAN_ALL";
    public static final String ACTION_MEDIA_SCANNER_SCAN_DIRECTORY = "oplus.intent.action.MEDIA_SCAN_DIRECTORY";
    public static final String ACTION_OPLUS_OTA_UPDATE_FAILED = "oplus.intent.action.OPLUS_OTA_UPDATE_FAILED";
    public static final String ACTION_OPLUS_OTA_UPDATE_SUCCESSED = "oplus.intent.action.OPLUS_OTA_UPDATE_SUCCESSED";
    public static final String ACTION_OPLUS_PACKAGE_ADDED = "oplus.intent.action.OPLUS_PACKAGE_ADDED";
    public static final String ACTION_OPLUS_RECOVER_UPDATE_FAILED = "oplus.intent.action.OPLUS_RECOVER_UPDATE_FAILED";
    public static final String ACTION_OPLUS_RECOVER_UPDATE_SUCCESSED = "oplus.intent.action.OPLUS_RECOVER_UPDATE_SUCCESSED";
    public static final String ACTION_PRE_MEDIA_SHARED = "android.intent.action.MEDIA_PRE_SHARED";
    public static final String ACTION_SAU_UPDATE_FAILED = "oplus.intent.action.OPLUS_SAU_UPDATE_FAILED";
    public static final String ACTION_SAU_UPDATE_SUCCESSED = "oplus.intent.action.OPLUS_SAU_UPDATE_SUCCESSED";
    public static final String ACTION_SCREEN_SHOT = "oplus.intent.action.SCREEN_SHOT";
    public static final String ACTION_SKIN_CHANGED = "oplus.intent.action.SKIN_CHANGED";
    public static final String ACTION_TRIGGER_PACKAGE = "android.intent.action.TRIGGER_PACKAGE";
    public static final String EXTRA_DATA_BRIGHTNESS = "LightBreightness";
    public static final String EXTRA_DATA_ID = "LightID";
    public static final String EXTRA_DUAL_SIM_MODE = "mode";
    public static final String EXTRA_LID_STATE = "lid_state";
    public static final String INTENT_CAMERA_OPEN_LIGHT = "com.oplus.camera.OpenLight";

    public static final int OPLUS_FALG_APP_DETAILS_SKIP = 0x1000;
    public static final int OPLUS_FLAG_ACTIVITY_SECURE_POLICY = 0x80000000;
    public static final int OPLUS_FLAG_CONTAINER_TASK = 0x10000;
    public static final int OPLUS_FLAG_GP_INTERCEPT_SKIP = 0x100;
    public static final int OPLUS_FLAG_MULTI_APP_SKIP_CHOOSER = 0x800;
    public static final int OPLUS_FLAG_MUTIL_APP = 0x400;
    public static final int OPLUS_FLAG_MUTIL_CHOOSER = 0x200;
    public static final int OPLUS_FLAG_POCKET_STUDIO_CANVAS = 0x4000;
    public static final int OPLUS_FLAG_POCKET_STUDIO_EMBEDDED = 0x8000;
    public static final int OPULS_FLAG_RECEIVER_QUEUE_PRIOR = 0x100000;

    public OplusIntent() {
    }

    public static int getCallingUid(Intent intent) {
        return 0;
    }

    public static int getOplusFlags(Intent intent) {
        return 0;
    }

    public static int getOplusUserId(Intent intent) {
        return 0;
    }

    public static int getSkipMultiAppChooser(Intent intent) {
        return 0;
    }

    public static void setOplusFlags(Intent intent, int oplusFlags) {
    }

    public static void setOplusUserId(Intent intent, int oplusUserId) {
    }

    public static void setSkipMultiAppChooser(Intent intent, int isSkip) {
    }
}
