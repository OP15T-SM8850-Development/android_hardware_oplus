/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.app;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IOplusSplitScreenObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusSplitScreenObserver";

    void onStateChanged(String event, Bundle data) throws RemoteException;

    public static abstract class Stub extends Binder implements IOplusSplitScreenObserver {
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
