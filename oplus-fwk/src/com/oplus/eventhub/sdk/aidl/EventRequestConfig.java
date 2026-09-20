package com.oplus.eventhub.sdk.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Closure stub for com.oplus.eventhub.sdk.aidl.EventRequestConfig (Parcelable request holding
 * the set of DeviceEvents to subscribe). Faithful field/parcel port.
 */
public class EventRequestConfig implements Parcelable {
    private ArraySet<DeviceEvent> mDeviceEventSet;

    public EventRequestConfig(ArraySet<DeviceEvent> events) {
        this.mDeviceEventSet = new ArraySet<DeviceEvent>();
        if (events != null && !events.isEmpty()) {
            this.mDeviceEventSet.addAll(events);
        }
    }

    @SuppressWarnings("unchecked")
    public EventRequestConfig(Parcel in) {
        ClassLoader loader = EventRequestConfig.class.getClassLoader();
        this.mDeviceEventSet = (ArraySet<DeviceEvent>) (ArraySet) in.readArraySet(loader);
    }

    public ArraySet<DeviceEvent> getDeviceEventSet() {
        if (this.mDeviceEventSet == null) {
            this.mDeviceEventSet = new ArraySet<DeviceEvent>();
        }
        return this.mDeviceEventSet;
    }

    public HashSet<Integer> getAllEvents() {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        if (this.mDeviceEventSet != null && !this.mDeviceEventSet.isEmpty()) {
            Iterator<DeviceEvent> it = this.mDeviceEventSet.iterator();
            while (it.hasNext()) {
                DeviceEvent event = it.next();
                hashSet.add(Integer.valueOf(event.getEventType()));
            }
        }
        return hashSet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeArraySet(this.mDeviceEventSet);
    }

    public static final Parcelable.Creator<EventRequestConfig> CREATOR =
            new Parcelable.Creator<EventRequestConfig>() {
                @Override
                public EventRequestConfig createFromParcel(Parcel in) {
                    return new EventRequestConfig(in);
                }

                @Override
                public EventRequestConfig[] newArray(int size) {
                    return new EventRequestConfig[size];
                }
            };
}
