/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.appcache;

import android.os.Bundle;

/**
 * Class-closure stub for the OEM com.oplus.appcache.OplusAppCacheManager.
 *
 * Hard-referenced by OppoGallery2 (com.oplus.aiunit.vision.ytr):
 *   getInstance()Lcom/oplus/appcache/OplusAppCacheManager;
 *   queryProviderCache(Ljava/lang/String;Landroid/os/Bundle;)Landroid/os/Bundle;
 *
 * OEM behavior: the real manager binds to the "oplus_app_cache_service" binder
 * service; when the service is unavailable it returns null from
 * queryProviderCache (const/4 v0,0x0 -> return-object v0). The Gallery call site
 * null-checks the result (move-result-object v1; if-eqz v1, ...), so returning
 * null is the safe, OEM-faithful default (no NPE).
 */
public class OplusAppCacheManager {
    private static volatile OplusAppCacheManager sInstance = null;

    private OplusAppCacheManager() {}

    public static OplusAppCacheManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusAppCacheManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusAppCacheManager();
                }
            }
        }
        return sInstance;
    }

    public Bundle queryProviderCache(String authority, Bundle extras) {
        return null;
    }
}
