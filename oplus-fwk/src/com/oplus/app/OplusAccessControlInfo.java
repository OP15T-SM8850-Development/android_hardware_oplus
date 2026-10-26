package com.oplus.app;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class OplusAccessControlInfo implements Parcelable {
    public static final Parcelable.Creator<OplusAccessControlInfo> CREATOR = new Parcelable.Creator<OplusAccessControlInfo>() { // from class: com.oplus.app.OplusAccessControlInfo.1
        @Override // android.os.Parcelable.Creator
        public OplusAccessControlInfo createFromParcel(Parcel in) {
            return new OplusAccessControlInfo(in);
        }

        @Override // android.os.Parcelable.Creator
        public OplusAccessControlInfo[] newArray(int size) {
            return new OplusAccessControlInfo[size];
        }
    };
    public boolean isEncrypted;
    public boolean isHideIcon;
    public boolean isHideInRecent;
    public boolean isHideNotice;
    public String mName;
    public int userId;

    public OplusAccessControlInfo() {
    }

    public OplusAccessControlInfo(Parcel in) {
        this.mName = in.readString();
        this.userId = in.readInt();
        this.isEncrypted = in.readByte() != 0;
        this.isHideIcon = in.readByte() != 0;
        this.isHideInRecent = in.readByte() != 0;
        this.isHideNotice = in.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeInt(this.userId);
        dest.writeByte((byte) (this.isEncrypted ? 1 : 0));
        dest.writeByte((byte) (this.isHideIcon ? 1 : 0));
        dest.writeByte((byte) (this.isHideInRecent ? 1 : 0));
        dest.writeByte((byte) (this.isHideNotice ? 1 : 0));
    }

    @Override
    public String toString() {
        return "OplusAccessControlInfo{mName=" + this.mName
                + ", userId=" + this.userId
                + ", isEncrypted=" + this.isEncrypted
                + ", isHideIcon=" + this.isHideIcon
                + ", isHideInRecent=" + this.isHideInRecent
                + ", isHideNotice=" + this.isHideNotice + "}";
    }
}
