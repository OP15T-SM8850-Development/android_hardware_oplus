/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.oshare;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

import java.util.List;

/**
 * Stub for OEM AIDL com.oplus.oshare.IOplusOshareCallback.
 * Modeled faithfully as an IInterface so consumer subclasses of Stub verify/link correctly.
 */
public interface IOplusOshareCallback extends IInterface {

    String DESCRIPTOR = "com.oplus.oshare.IOplusOshareCallback";

    void onDeviceChanged(List devices);

    void onSendSwitchChanged(boolean on);

    abstract class Stub extends Binder implements IOplusOshareCallback {

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IOplusOshareCallback asInterface(IBinder obj) {
            return null;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
