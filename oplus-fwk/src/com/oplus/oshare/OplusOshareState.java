/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.oshare;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Stub for OEM com.oplus.oshare.OplusOshareState (Parcelable enum).
 * Enum constant order preserved from OOS oplus-framework.jar &lt;clinit&gt; to keep ordinals stable.
 * Note: OEM ships both the misspelled BUSUY and BUSY constants; both are retained faithfully.
 */
public enum OplusOshareState implements Parcelable {
    IDLE,
    READY,
    TRANSIT_WAIT,
    TRANSITING,
    CANCEL,
    TRANSIT_SUCCESS,
    TRANSIT_FAILED,
    TRANSIT_REJECT,
    TRANSIT_TIMEOUT,
    BUSUY,
    BUSY,
    CANCEL_WAIT,
    SPACE_NOT_ENOUGH;

    public static final Parcelable.Creator<OplusOshareState> CREATOR =
            new Parcelable.Creator<OplusOshareState>() {
        @Override
        public OplusOshareState createFromParcel(Parcel source) {
            int i = source.readInt();
            OplusOshareState[] values = OplusOshareState.values();
            if (i < 0 || i >= values.length) {
                return OplusOshareState.IDLE;
            }
            return values[i];
        }

        @Override
        public OplusOshareState[] newArray(int size) {
            return new OplusOshareState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
