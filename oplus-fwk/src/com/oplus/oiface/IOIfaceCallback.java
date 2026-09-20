package com.oplus.oiface;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Closure stub for the OEM OIface game-performance callback AIDL interface
 * com.oplus.oiface.IOIfaceCallback and its generated Stub.
 *
 * Consumer: OppoGallery2 ships its own concrete subclass of IOIfaceCallback.Stub
 * (new-instance + invoke-direct Stub.<init>()V) and passes it to
 * OifaceManager.registerOifaceCallback(IOIfaceCallback). For that subclass to load
 * and verify, this interface must exist, extend IInterface, and declare the exact
 * 11 abstract callback methods it overrides; Stub must exist as a concrete-enough
 * abstract Binder subclass with a callable no-arg constructor and a concrete
 * asBinder() so the subclass is not left abstract-incomplete.
 *
 * The onTransact wire format (transaction ids 0x1..0xb, read/write order) is ported
 * byte-for-byte from the real oplus-framework.jar Stub so that, should the OIface
 * service ever dispatch a callback, it routes into the consumer subclass overrides
 * correctly. Method throws clauses match the OEM (RemoteException).
 */
public interface IOIfaceCallback extends IInterface {

    String DESCRIPTOR = "com.oplus.oiface.IOIfaceCallback";

    String onFBNotification(int arg0) throws RemoteException;

    String onGPANotification(String arg0) throws RemoteException;

    void onTGPAInfo(String arg0, int arg1, int arg2) throws RemoteException;

    void onHyperBoostInfo(String arg0, int arg1, int arg2) throws RemoteException;

    void onEngineBoostINfo(String arg0, int arg1, int arg2) throws RemoteException;

    void onOifaceGeneralInfo(String arg0, int arg1, int arg2, int arg3) throws RemoteException;

    void onGameStatusChanged(String arg0, String arg1) throws RemoteException;

    void onNetworkChanged(String arg0, int arg1) throws RemoteException;

    void onSystemNotify(String arg0) throws RemoteException;

    void onThermalStatusChanged(String arg0) throws RemoteException;

    void onGameJitter(String arg0, int arg1) throws RemoteException;

    abstract class Stub extends Binder implements IOIfaceCallback {

        static final int TRANSACTION_onFBNotification = IBinder.FIRST_CALL_TRANSACTION + 0;
        static final int TRANSACTION_onGPANotification = IBinder.FIRST_CALL_TRANSACTION + 1;
        static final int TRANSACTION_onTGPAInfo = IBinder.FIRST_CALL_TRANSACTION + 2;
        static final int TRANSACTION_onHyperBoostInfo = IBinder.FIRST_CALL_TRANSACTION + 3;
        static final int TRANSACTION_onEngineBoostINfo = IBinder.FIRST_CALL_TRANSACTION + 4;
        static final int TRANSACTION_onOifaceGeneralInfo = IBinder.FIRST_CALL_TRANSACTION + 5;
        static final int TRANSACTION_onGameStatusChanged = IBinder.FIRST_CALL_TRANSACTION + 6;
        static final int TRANSACTION_onNetworkChanged = IBinder.FIRST_CALL_TRANSACTION + 7;
        static final int TRANSACTION_onSystemNotify = IBinder.FIRST_CALL_TRANSACTION + 8;
        static final int TRANSACTION_onThermalStatusChanged = IBinder.FIRST_CALL_TRANSACTION + 9;
        static final int TRANSACTION_onGameJitter = IBinder.FIRST_CALL_TRANSACTION + 10;

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IOIfaceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin instanceof IOIfaceCallback) {
                return (IOIfaceCallback) iin;
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
                case TRANSACTION_onFBNotification: {
                    int arg0 = data.readInt();
                    data.enforceNoDataAvail();
                    String result = this.onFBNotification(arg0);
                    reply.writeNoException();
                    reply.writeString(result);
                    return true;
                }
                case TRANSACTION_onGPANotification: {
                    String arg0 = data.readString();
                    data.enforceNoDataAvail();
                    String result = this.onGPANotification(arg0);
                    reply.writeNoException();
                    reply.writeString(result);
                    return true;
                }
                case TRANSACTION_onTGPAInfo: {
                    String arg0 = data.readString();
                    int arg1 = data.readInt();
                    int arg2 = data.readInt();
                    data.enforceNoDataAvail();
                    this.onTGPAInfo(arg0, arg1, arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_onHyperBoostInfo: {
                    String arg0 = data.readString();
                    int arg1 = data.readInt();
                    int arg2 = data.readInt();
                    data.enforceNoDataAvail();
                    this.onHyperBoostInfo(arg0, arg1, arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_onEngineBoostINfo: {
                    String arg0 = data.readString();
                    int arg1 = data.readInt();
                    int arg2 = data.readInt();
                    data.enforceNoDataAvail();
                    this.onEngineBoostINfo(arg0, arg1, arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_onOifaceGeneralInfo: {
                    String arg0 = data.readString();
                    int arg1 = data.readInt();
                    int arg2 = data.readInt();
                    int arg3 = data.readInt();
                    data.enforceNoDataAvail();
                    this.onOifaceGeneralInfo(arg0, arg1, arg2, arg3);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_onGameStatusChanged: {
                    String arg0 = data.readString();
                    String arg1 = data.readString();
                    data.enforceNoDataAvail();
                    this.onGameStatusChanged(arg0, arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_onNetworkChanged: {
                    String arg0 = data.readString();
                    int arg1 = data.readInt();
                    data.enforceNoDataAvail();
                    this.onNetworkChanged(arg0, arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_onSystemNotify: {
                    String arg0 = data.readString();
                    data.enforceNoDataAvail();
                    this.onSystemNotify(arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_onThermalStatusChanged: {
                    String arg0 = data.readString();
                    data.enforceNoDataAvail();
                    this.onThermalStatusChanged(arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_onGameJitter: {
                    String arg0 = data.readString();
                    int arg1 = data.readInt();
                    data.enforceNoDataAvail();
                    this.onGameJitter(arg0, arg1);
                    reply.writeNoException();
                    return true;
                }
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }
}
