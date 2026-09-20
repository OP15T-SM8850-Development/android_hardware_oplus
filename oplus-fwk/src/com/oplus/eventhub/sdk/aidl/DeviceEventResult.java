package com.oplus.eventhub.sdk.aidl;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Closure stub for com.oplus.eventhub.sdk.aidl.DeviceEventResult (Parcelable result payload
 * delivered to EventCallback.onEventStateChanged). Faithful field/parcel port.
 */
public class DeviceEventResult implements Parcelable {
    private static final String TAG = "DeviceEventResult";

    private int mEventType;
    private int mEventStateType;
    private int mPid;
    private String mPkgName;
    private Bundle mExtraData;

    public DeviceEventResult(int eventType, int eventStateType, int pid, String pkgName,
            Bundle bundle) {
        this.mEventType = eventType;
        this.mEventStateType = eventStateType;
        this.mPid = pid;
        this.mPkgName = pkgName;
        this.mExtraData = bundle;
    }

    public DeviceEventResult(Parcel in) {
        this.mEventType = in.readInt();
        this.mEventStateType = in.readInt();
        this.mPid = in.readInt();
        this.mPkgName = in.readString();
        this.mExtraData = in.readBundle(getClass().getClassLoader());
    }

    public int getEventType() {
        return this.mEventType;
    }

    public int getEventStateType() {
        return this.mEventStateType;
    }

    public int getPid() {
        return this.mPid;
    }

    public String getPkgName() {
        return this.mPkgName;
    }

    public Bundle getExtraData() {
        return this.mExtraData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.mEventType);
        dest.writeInt(this.mEventStateType);
        dest.writeInt(this.mPid);
        dest.writeString(this.mPkgName);
        dest.writeBundle(this.mExtraData);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DeviceEventResult :");
        sb.append("\teventType is :").append(this.mEventType);
        sb.append("\teventStateType is :").append(this.mEventStateType);
        sb.append("\tpid is : ").append(this.mPid);
        sb.append("\t\tpackageName is : ").append(this.mPkgName);
        if (this.mExtraData != null) {
            sb.append("\tExtraData is : ").append(this.mExtraData.toString());
        }
        return sb.toString();
    }

    public static final Parcelable.Creator<DeviceEventResult> CREATOR =
            new Parcelable.Creator<DeviceEventResult>() {
                @Override
                public DeviceEventResult createFromParcel(Parcel in) {
                    return new DeviceEventResult(in);
                }

                @Override
                public DeviceEventResult[] newArray(int size) {
                    return new DeviceEventResult[size];
                }
            };
}
