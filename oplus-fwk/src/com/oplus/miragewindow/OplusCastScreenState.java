package com.oplus.miragewindow;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* Faithful port of OEM com.oplus.miragewindow.OplusCastScreenState (Parcelable data class). */
public class OplusCastScreenState implements Parcelable {
    public static final int INITIALIZING = 1;
    public static final int RUNNING = 2;
    public static final int FINISHED = 3;

    public String castName;
    public int castState;
    public Bundle extension = new Bundle();

    public OplusCastScreenState() {
    }

    protected OplusCastScreenState(Parcel in) {
        castName = in.readString();
        castState = in.readInt();
        extension = in.readBundle();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(castName);
        dest.writeInt(castState);
        dest.writeBundle(extension);
    }

    @Override
    public String toString() {
        return "OplusCastScreenState = { " + " castName = " + castName
                + " castState = " + castState
                + " extension = " + extension + "}";
    }

    public static final Parcelable.Creator<OplusCastScreenState> CREATOR =
            new Parcelable.Creator<OplusCastScreenState>() {
                @Override
                public OplusCastScreenState createFromParcel(Parcel in) {
                    return new OplusCastScreenState(in);
                }

                @Override
                public OplusCastScreenState[] newArray(int size) {
                    return new OplusCastScreenState[size];
                }
            };
}
