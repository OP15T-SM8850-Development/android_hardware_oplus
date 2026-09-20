/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.oshare;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/**
 * Stub for OEM AIDL com.oplus.oshare.IOplusOshareService.
 * Method surface limited to what shipped consumers reference (the multi-callback
 * variants are intentionally omitted to keep OplusOshareServiceUtil's obligation minimal).
 */
public interface IOplusOshareService extends IInterface {

    String DESCRIPTOR = "com.oplus.oshare.IOplusOshareService";

    void cancelTask(OplusOshareDevice device);

    boolean isSendOn();

    void pause();

    void registerCallback(IOplusOshareCallback callback);

    void resume();

    void scan();

    void sendData(Intent intent, OplusOshareDevice device);

    void stop();

    void switchSend(boolean on);

    void unregisterCallback(IOplusOshareCallback callback);

    abstract class Stub extends Binder implements IOplusOshareService {

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IOplusOshareService asInterface(IBinder obj) {
            return null;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
