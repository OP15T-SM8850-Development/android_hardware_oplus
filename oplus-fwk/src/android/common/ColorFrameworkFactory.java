/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package android.common;

import android.content.Context;

import com.coloros.deepthinker.IColorDeepThinkerManager;

/**
 * Stub of the OEM boot-classpath class android.common.ColorFrameworkFactory.
 *
 * Missed by the prior member-diff audit (it is only reached through class-load refs:
 * a getInstance() return type, a wrapper field type, an invoke-virtual receiver and as the
 * superclass of oplus.android.OplusFrameworkFactoryImpl) -> absence would be a
 * NoClassDefFoundError in OppoGallery2.
 *
 * Consumer refs (OppoGallery2 com.heytap.addon.OplusFrameworkFactoryImpl):
 *   static  getInstance()Landroid/common/ColorFrameworkFactory;      -> non-null singleton
 *   virtual getColorDeepThinkerManager(Context)Lcom/coloros/deepthinker/IColorDeepThinkerManager;
 * The getColorDeepThinkerManager call lives in the pre-R branch (VersionUtils.greaterOrEqualsToR()
 * is always true on this build, so it never executes), but it is declared for provable closure;
 * it returns null and the wrapper null-checks the result.
 */
public class ColorFrameworkFactory {
    private static ColorFrameworkFactory sInstance = null;

    public ColorFrameworkFactory() {
    }

    public static ColorFrameworkFactory getInstance() {
        if (sInstance == null) {
            sInstance = new ColorFrameworkFactory();
        }
        return sInstance;
    }

    public IColorDeepThinkerManager getColorDeepThinkerManager(Context context) {
        return null;
    }
}
