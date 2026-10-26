package com.oplus.osense.info;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class OsenseSaRequest implements Parcelable {

    private String mScene = "";
    private String mAction = "";
    private int mTimeout = -1;
    private Bundle mInfo = null;

    public OsenseSaRequest() {
    }

    public OsenseSaRequest(Bundle info) {
        this.mInfo = info;
    }

    protected OsenseSaRequest(Parcel in) {
        readFromParcel(in);
    }

    public OsenseSaRequest(String scene, String action, int timeout) {
        this.mScene = scene;
        this.mAction = action;
        this.mTimeout = timeout;
    }

    public static final Creator<OsenseSaRequest> CREATOR = new Creator<OsenseSaRequest>() {
        @Override
        public OsenseSaRequest createFromParcel(Parcel in) {
            return new OsenseSaRequest(in);
        }

        @Override
        public OsenseSaRequest[] newArray(int size) {
            return new OsenseSaRequest[size];
        }
    };

    public String getScene() {
        return this.mScene;
    }

    public String getAction() {
        return this.mAction;
    }

    public int getTimeout() {
        return this.mTimeout;
    }

    public Bundle getInfo() {
        return this.mInfo;
    }

    protected void readFromParcel(Parcel in) {
        this.mScene = in.readString();
        this.mAction = in.readString();
        this.mTimeout = in.readInt();
        this.mInfo = in.readBundle();
    }

    @Override
    public String toString() {
        return "OsenseSaRequest{scene='" + this.mScene + '\'' + ", action='" + this.mAction
                + '\'' + ", timeout=" + this.mTimeout + ", info=" + this.mInfo + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mScene);
        dest.writeString(this.mAction);
        dest.writeInt(this.mTimeout);
        dest.writeBundle(this.mInfo);
    }
}
