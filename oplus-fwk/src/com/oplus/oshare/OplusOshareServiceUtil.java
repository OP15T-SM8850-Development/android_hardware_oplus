/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.oshare;

import android.content.Context;
import android.content.Intent;

/**
 * Stub for OEM com.oplus.oshare.OplusOshareServiceUtil.
 * Extends IOplusOshareService.Stub (faithful to OOS) and no-ops the entire OShare
 * surface: the service is never bound, so no callback ever fires and the nearby-share
 * UI stays inert without crashing. Implements every IOplusOshareService method so the
 * concrete class is instantiable.
 */
public class OplusOshareServiceUtil extends IOplusOshareService.Stub {

    private Context mContext;
    private IOplusOshareInitListener mInitListener;
    private IOplusOshareCallback mOShareCallback;

    public OplusOshareServiceUtil(Context context, IOplusOshareInitListener initListener) {
        mContext = context;
        mInitListener = initListener;
    }

    public void initShareEngine() {}

    @Override
    public void cancelTask(OplusOshareDevice device) {}

    @Override
    public boolean isSendOn() {
        return false;
    }

    @Override
    public void pause() {}

    @Override
    public void registerCallback(IOplusOshareCallback callback) {
        mOShareCallback = callback;
    }

    @Override
    public void resume() {}

    @Override
    public void scan() {}

    @Override
    public void sendData(Intent intent, OplusOshareDevice device) {}

    @Override
    public void stop() {}

    @Override
    public void switchSend(boolean on) {}

    @Override
    public void unregisterCallback(IOplusOshareCallback callback) {
        mOShareCallback = null;
    }
}
