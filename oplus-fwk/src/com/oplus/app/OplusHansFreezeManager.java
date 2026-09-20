/*
 * Copyright (C) 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.app;

import android.content.Context;

/*
 * Stub — Hans is the OOS background freeze/hibernation service. AIUnit's sysboard
 * FreezeSubscriber calls keepBackgroundRunning() to shield long-running detect
 * sessions from being frozen. LineageOS has no Hans, so background execution is
 * never frozen in the first place: report success on the protect connection so
 * callers waiting on the callback proceed instead of timing out.
 *
 * Descriptors mirrored from the OOS 16.0.8 consumers (AIUnit 16.1.25 smali):
 *   getInstance()Lcom/oplus/app/OplusHansFreezeManager;
 *   keepBackgroundRunning(Landroid/content/Context;Ljava/lang/String;Z
 *       Lcom/oplus/app/IOplusProtectConnection;)V
 */
public class OplusHansFreezeManager {
    private static final OplusHansFreezeManager INSTANCE = new OplusHansFreezeManager();

    private OplusHansFreezeManager() {
    }

    public static OplusHansFreezeManager getInstance() {
        return INSTANCE;
    }

    public void keepBackgroundRunning(Context context, String packageName, boolean protect,
            IOplusProtectConnection connection) {
        // No Hans on LineageOS: the caller is already permanently "protected".
        if (connection != null) {
            try {
                connection.onSuccess();
            } catch (Exception e) {
                // Callback into the caller's own process; nothing to recover.
            }
        }
    }
}
