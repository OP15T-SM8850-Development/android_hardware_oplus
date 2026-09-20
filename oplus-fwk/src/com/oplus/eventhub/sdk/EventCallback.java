package com.oplus.eventhub.sdk;

import com.oplus.eventhub.sdk.aidl.DeviceEventResult;
import com.oplus.eventhub.sdk.aidl.IEventCallback;

/**
 * Closure stub for the OEM EventHub SDK callback base class.
 * Consumers (OppoGallery2) subclass this and implement onEventStateChanged.
 * Extends the AIDL Stub so instances are valid Binders passed to the eventhub service.
 */
public abstract class EventCallback extends IEventCallback.Stub {

    public EventCallback() {
        super();
    }

    public abstract void onEventStateChanged(DeviceEventResult result);
}
