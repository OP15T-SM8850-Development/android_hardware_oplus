package com.oplus.osense.eventinfo;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class OsenseEventResult implements Parcelable {
    private static final String TAG = "OsenseEventResult";

    private int mEventType;
    private int mEventStateType;
    private Bundle mExtraData;

    public OsenseEventResult(int eventType, int eventStateType, Bundle bundle) {
        this.mEventType = eventType;
        this.mEventStateType = eventStateType;
        this.mExtraData = bundle;
    }

    public OsenseEventResult(Parcel in) {
        this.mEventType = in.readInt();
        this.mEventStateType = in.readInt();
        this.mExtraData = in.readBundle(getClass().getClassLoader());
    }

    public static final Creator<OsenseEventResult> CREATOR = new Creator<OsenseEventResult>() {
        @Override
        public OsenseEventResult createFromParcel(Parcel in) {
            return new OsenseEventResult(in);
        }

        @Override
        public OsenseEventResult[] newArray(int size) {
            return new OsenseEventResult[size];
        }
    };

    public int getEventType() {
        return this.mEventType;
    }

    public int getEventStateType() {
        return this.mEventStateType;
    }

    public Bundle getExtraData() {
        if (this.mExtraData == null) {
            Log.w(TAG, "this event extra data is null");
        }
        return this.mExtraData;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("OsenseEventResult :");
        stringBuilder.append("\teventType is :");
        stringBuilder.append(this.mEventType);
        stringBuilder.append("\teventStateType is :");
        stringBuilder.append(this.mEventStateType);
        if (this.mExtraData != null) {
            stringBuilder.append("\tExtraData is : ");
            stringBuilder.append(this.mExtraData.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.mEventType);
        dest.writeInt(this.mEventStateType);
        dest.writeBundle(this.mExtraData);
    }
}
