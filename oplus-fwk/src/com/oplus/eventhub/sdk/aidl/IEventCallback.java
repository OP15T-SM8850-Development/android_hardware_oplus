package com.oplus.eventhub.sdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Closure stub for the OEM EventHub callback AIDL interface + generated Stub.
 * Wire format (transaction id, readTypedObject) mirrors the OEM codegen so callbacks
 * from the eventhub service dispatch correctly into consumer subclasses (EventCallback).
 */
public interface IEventCallback extends IInterface {

    String DESCRIPTOR = "com.oplus.eventhub.sdk.aidl.IEventCallback";

    void onEventStateChanged(DeviceEventResult result) throws RemoteException;

    abstract class Stub extends Binder implements IEventCallback {

        static final int TRANSACTION_onEventStateChanged = IBinder.FIRST_CALL_TRANSACTION + 0;

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IEventCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin instanceof IEventCallback) {
                return (IEventCallback) iin;
            }
            return null;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
                throws RemoteException {
            String descriptor = DESCRIPTOR;
            if (code >= IBinder.FIRST_CALL_TRANSACTION && code <= IBinder.LAST_CALL_TRANSACTION) {
                data.enforceInterface(descriptor);
            }
            if (code == INTERFACE_TRANSACTION) {
                reply.writeString(descriptor);
                return true;
            }
            switch (code) {
                case TRANSACTION_onEventStateChanged: {
                    DeviceEventResult arg0 = data.readTypedObject(DeviceEventResult.CREATOR);
                    data.enforceNoDataAvail();
                    this.onEventStateChanged(arg0);
                    return true;
                }
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }
}
