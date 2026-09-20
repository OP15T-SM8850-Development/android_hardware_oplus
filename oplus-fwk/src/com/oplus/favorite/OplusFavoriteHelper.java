package com.oplus.favorite;

import android.content.Context;

import com.oplus.direct.IOplusDirectFindCallback;

/**
 * Closure stub for the OEM favorite/direct-find entry point hard-referenced by
 * OppoGallery2 (via com.heytap.addon.favorite.OplusFavoriteHelper). All members
 * are the exact static shapes the gallery resolves, so class-load + linkage
 * always succeed. The favorite/direct-find crawl feature is not enabled on this
 * build: isSettingOn reports off (safe default per feature policy) and the
 * startCrawl/startSave entry points are non-crashing no-ops.
 */
public class OplusFavoriteHelper {

    public static final String TAG = "OplusFavoriteHelper";

    public OplusFavoriteHelper() {
    }

    public static boolean isSettingOn(Context context) {
        return false;
    }

    public static void startCrawl(IOplusDirectFindCallback callback) {
    }

    public static void startSave(IOplusDirectFindCallback callback) {
    }
}
