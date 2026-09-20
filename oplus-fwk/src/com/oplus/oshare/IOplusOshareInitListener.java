/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.oshare;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/**
 * Stub for OEM AIDL com.oplus.oshare.IOplusOshareInitListener.
 */
public interface IOplusOshareInitListener extends IInterface {

    String DESCRIPTOR = "com.oplus.oshare.IOplusOshareInitListener";

    void onShareInit();

    void onShareUninit();

    abstract class Stub extends Binder implements IOplusOshareInitListener {

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IOplusOshareInitListener asInterface(IBinder obj) {
            return null;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
