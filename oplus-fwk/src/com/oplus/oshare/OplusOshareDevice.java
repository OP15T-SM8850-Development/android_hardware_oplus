/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.oshare;

import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Stub for OEM com.oplus.oshare.OplusOshareDevice (Parcelable data class).
 * Field set/getters/setters ported faithfully from OOS oplus-framework.jar.
 * Parcel marshalling is a no-op (self-consistent) since our OShare service is inert.
 */
public class OplusOshareDevice implements Parcelable {

    public static final int DEFAULT_VIRTUAL = 0x8;

    private String mBleMac;
    private BluetoothDevice mBluetootchDevice;
    private String mDisplayName;
    private Bitmap mHeadIcon;
    private String mHeadIconUrl;
    private long mLastFoundTime;
    private String mName;
    private int mProgress;
    private String mRemainTime;
    private OplusOshareState mState;
    private int mSucceedNum;
    private int mTotalNum;
    private int mVender;
    private int mVirtual;
    private String mWifiMac;

    public static final Parcelable.Creator<OplusOshareDevice> CREATOR =
            new Parcelable.Creator<OplusOshareDevice>() {
        @Override
        public OplusOshareDevice createFromParcel(Parcel source) {
            return new OplusOshareDevice();
        }

        @Override
        public OplusOshareDevice[] newArray(int size) {
            return new OplusOshareDevice[size];
        }
    };

    public OplusOshareDevice() {}

    public void copyFrom(OplusOshareDevice other) {}

    public String getBleMac() { return mBleMac; }
    public void setBleMac(String v) { mBleMac = v; }

    public BluetoothDevice getBluetoothDevice() { return mBluetootchDevice; }
    public void setBluetootchDevice(BluetoothDevice v) { mBluetootchDevice = v; }

    public String getDisplayName() { return mDisplayName; }
    public void setDisplayName(String v) { mDisplayName = v; }

    public Bitmap getHeadIcon() { return mHeadIcon; }
    public void setHeadIcon(Bitmap v) { mHeadIcon = v; }

    public String getHeadIconUrl() { return mHeadIconUrl; }
    public void setHeadIconUrl(String v) { mHeadIconUrl = v; }

    public long getLastFoundTime() { return mLastFoundTime; }
    public void setLastFoundTime(long v) { mLastFoundTime = v; }

    public String getName() { return mName; }
    public void setName(String v) { mName = v; }

    public int getProgress() { return mProgress; }
    public void setProgress(int v) { mProgress = v; }

    public String getRemainTime() { return mRemainTime; }
    public void setRemainTime(String v) { mRemainTime = v; }

    public OplusOshareState getState() { return mState; }
    public void setState(OplusOshareState v) { mState = v; }

    public int getSucceedNum() { return mSucceedNum; }
    public void setSucceedNum(int v) { mSucceedNum = v; }

    public int getTotalNum() { return mTotalNum; }
    public void setTotalNum(int v) { mTotalNum = v; }

    public int getVender() { return mVender; }
    public void setVender(int v) { mVender = v; }

    public int getVirtual() { return mVirtual; }
    public void setVirtual(int v) { mVirtual = v; }

    public boolean isVirtual() { return mVirtual == DEFAULT_VIRTUAL; }

    public String getWifiMac() { return mWifiMac; }
    public void setWifiMac(String v) { mWifiMac = v; }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public String toString() {
        return "OplusOshareDevice{mName=" + mName + "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {}

    public void readFromParcel(Parcel in) {}
}
