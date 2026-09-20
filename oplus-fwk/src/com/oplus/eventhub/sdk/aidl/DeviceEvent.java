package com.oplus.eventhub.sdk.aidl;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Closure stub for com.oplus.eventhub.sdk.aidl.DeviceEvent (Parcelable data class).
 * Faithfully ports the OEM field layout / parcel format so marshalling stays compatible.
 */
public class DeviceEvent implements Parcelable {
    private static final String TAG = "DeviceEvent";

    private int mEventType;
    private int mEventStateType;

    private DeviceEvent(int eventType, int eventStateType) {
        this.mEventType = eventType;
        this.mEventStateType = eventStateType;
    }

    public DeviceEvent(Parcel in) {
        this.mEventType = in.readInt();
        this.mEventStateType = in.readInt();
    }

    public int getEventType() {
        return this.mEventType;
    }

    public int getEventStateType() {
        return this.mEventStateType;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (object instanceof DeviceEvent) {
            DeviceEvent event = (DeviceEvent) object;
            return this.mEventType == event.getEventType()
                    && this.mEventStateType == event.getEventStateType();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{
                Integer.valueOf(this.mEventType),
                Integer.valueOf(this.mEventStateType)});
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.mEventType);
        dest.writeInt(this.mEventStateType);
    }

    public static final Parcelable.Creator<DeviceEvent> CREATOR =
            new Parcelable.Creator<DeviceEvent>() {
                @Override
                public DeviceEvent createFromParcel(Parcel in) {
                    return new DeviceEvent(in);
                }

                @Override
                public DeviceEvent[] newArray(int size) {
                    return new DeviceEvent[size];
                }
            };

    public static class Builder {
        private int mEventType = -1;
        private int mEventStateType = -1;

        public Builder() {
        }

        public Builder setEventType(int eventType) {
            this.mEventType = eventType;
            return this;
        }

        public Builder setEventStateType(int eventStateType) {
            this.mEventStateType = eventStateType;
            return this;
        }

        public DeviceEvent build() {
            if (this.mEventStateType == -1) {
                this.mEventStateType = 0;
            }
            return new DeviceEvent(this.mEventType, this.mEventStateType);
        }
    }
}
