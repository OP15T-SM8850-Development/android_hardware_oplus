/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.hardware.cryptoeng;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

import vendor.oplus.hardware.cryptoeng.ICryptoeng;

public class CryptoEngManager {
    private static final String TAG = "CryptoEngManager";

    private static volatile CryptoEngManager sInstance = null;
    private static String sServiceName = ICryptoeng.DESCRIPTOR + "/default";

    private volatile ICryptoeng mCryptoEngService;

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.i(TAG, sServiceName + " binderDied");
            synchronized (CryptoEngManager.class) {
                mCryptoEngService = null;
            }
        }
    };

    private CryptoEngManager() {}

    public static CryptoEngManager getInstance() {
        if (sInstance == null) {
            synchronized (CryptoEngManager.class) {
                if (sInstance == null) {
                    sInstance = new CryptoEngManager();
                }
            }
        }
        return sInstance;
    }

    private synchronized ICryptoeng getService() {
        if (mCryptoEngService == null) {
            IBinder binder = ServiceManager.getService(sServiceName);
            if (binder == null) {
                Log.w(TAG, "getService fail." + sServiceName);
                return null;
            }
            try {
                binder.linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                Log.e(TAG, "linkToDeath fail ", e);
                return null;
            }
            mCryptoEngService = ICryptoeng.Stub.asInterface(binder);
            if (mCryptoEngService == null) {
                Log.e(TAG, "asInterface fail.");
            }
        }
        return mCryptoEngService;
    }

    public byte[] cryptoEngCommand(byte[] inData) {
        try {
            ICryptoeng service = getService();
            if (service != null) {
                return service.cryptoeng_invoke_command(inData);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "get_project failed.", e);
        }
        return null;
    }

    public static class CommandId {
        public static final byte CE_CMD_GOOGLE_ATTESTATION_WRITE = 0x3;
        public static final byte CE_CMD_GOOGLE_ATTESTATION_VERIFY = 0x4;
        public static final byte CE_CMD_FINDPHONE_GET_STATUS = 0x12;
        public static final byte CE_CMD_GENERATE_PKI_CERT = 0x18;
        public static final byte CE_CMD_VERIFY_PKI_CERT = 0x19;
        public static final byte CE_CMD_HDCP_KEY_WRITE = 0x33;
        public static final byte CE_CMD_HDCP_KEY_VERIFY = 0x34;
        public static final byte CE_CMD_CLEAN_UP = 0x35;
        public static final byte CE_CMD_GET_SECURETYPE = 0x36;
        public static final byte CE_CMD_WIDEVINE_SUPPORT = 0x3b;
        public static final byte CE_CMD_CRYPTO_SUPPORT = 0x3c;
        public static final byte CE_CMD_ENGINEER = 0x5a;

        public CommandId() {}
    }
}
