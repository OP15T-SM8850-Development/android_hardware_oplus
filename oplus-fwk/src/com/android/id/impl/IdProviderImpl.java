package com.android.id.impl;

import android.app.Activity;
import android.content.Context;

/**
 * Stub for the OEM OpenID / OAID provider (real impl lives in oplus-framework.jar).
 * Consumers (OppoGallery2, OplusCamera) hard-reference this class for advertising /
 * stable-device IDs used by cloud/analytics paths. The real impl brokers to
 * android.app.OplusNotificationManager#getStdid and the com.heytap.openid OAID
 * ContentProvider; neither exists on our build, so every getter returns a safe,
 * non-crashing default (empty ID => callers treat it as "no ID available" and skip).
 * These are NOT feature gates, so the ORACLE default (empty/false) applies.
 */
public class IdProviderImpl {

    public IdProviderImpl() {
    }

    public String getGUID(Context context) {
        return "";
    }

    public String getOUID(Context context) {
        return "";
    }

    public String getDUID(Context context) {
        return "";
    }

    public String getAUID(Context context) {
        return "";
    }

    public String getAPID(Context context) {
        return "";
    }

    public String getOpenid(Context context, String type) {
        return "";
    }

    public String getStdid(Context context, String type) {
        return "";
    }

    public boolean checkGetGUID(Context context) {
        return false;
    }

    public boolean checkGetAPID(Context context) {
        return false;
    }

    public boolean checkGetOpenid(Context context, String type) {
        return false;
    }

    public boolean checkGetStdid(Context context, String type) {
        return false;
    }

    public static int checkSelfOAIDPermission(Context context) {
        return -2;
    }

    public static void requestOAIDPermission(Activity activity, int requestCode) {
    }
}
