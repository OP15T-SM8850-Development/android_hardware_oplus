/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package oplus.android;

import android.common.ColorFrameworkFactory;
import android.content.Context;

import com.oplus.deepthinker.IOplusDeepThinkerManager;

/**
 * Stub of the OEM boot-classpath class oplus.android.OplusFrameworkFactoryImpl.
 *
 * Consumer: OppoGallery2 (com.heytap.addon.OplusFrameworkFactoryImpl wrapper) hard-loads
 * this class via new-instance/invoke-static getInstance/invoke-virtual and check-casts the
 * getInstance() result to this type. It therefore MUST:
 *   - be an instantiable class whose getInstance() returns a real OplusFrameworkFactoryImpl
 *     instance (the wrapper does check-cast v0, Loplus/android/OplusFrameworkFactoryImpl;),
 *   - be assignable to android.common.ColorFrameworkFactory (getInstance descriptor returns
 *     Landroid/common/ColorFrameworkFactory; and the object doubles as the wrappers
 *     colorFrameworkFactory field) -> hence it extends ColorFrameworkFactory.
 *
 * In OOS this extends android.common.OplusFrameworkFactory (which itself extends
 * ColorFrameworkFactory); no shipped consumer references this object as
 * android.common.OplusFrameworkFactory, so we extend ColorFrameworkFactory directly to keep
 * the closure self-contained without touching the existing OplusFrameworkFactory stub.
 *
 * getOplusDeepThinkerManager() returns null; the wrapper null-checks the result
 * (if-eqz p0 -> return null), so null is the safe non-crashing default.
 */
public class OplusFrameworkFactoryImpl extends ColorFrameworkFactory {
    private static OplusFrameworkFactoryImpl sInstance = null;

    public OplusFrameworkFactoryImpl() {
    }

    public static ColorFrameworkFactory getInstance() {
        if (sInstance == null) {
            sInstance = new OplusFrameworkFactoryImpl();
        }
        return sInstance;
    }

    public IOplusDeepThinkerManager getOplusDeepThinkerManager(Context context) {
        return null;
    }
}
