package com.oplus.direct;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Closure stub for the OEM AIDL callback interface referenced (transitively) by
 * OppoGallery2 via {@link com.oplus.direct.OplusDirectFindCallback}. Faithful to the
 * OOS oplus-framework.jar AIDL shape so class-load + linkage always resolve.
 */
public interface IOplusDirectFindCallback extends IInterface {

    public static final String DESCRIPTOR = "com.oplus.direct.IOplusDirectFindCallback";

    void onDirectInfoFound(OplusDirectFindResult result) throws RemoteException;

    /** Default no-op implementation. */
    public static class Default implements IOplusDirectFindCallback {
        @Override
        public void onDirectInfoFound(OplusDirectFindResult result) throws RemoteException {
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    }

    /** Local-side IPC implementation stub class. */
    public static abstract class Stub extends Binder implements IOplusDirectFindCallback {

        static final int TRANSACTION_onDirectInfoFound = (IBinder.FIRST_CALL_TRANSACTION + 0);

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IOplusDirectFindCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && iin instanceof IOplusDirectFindCallback) {
                return (IOplusDirectFindCallback) iin;
            }
            return new Stub.Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case TRANSACTION_onDirectInfoFound:
                    return "onDirectInfoFound";
                default:
                    return null;
            }
        }

        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        public int getMaxTransactionId() {
            return 0;
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
                case TRANSACTION_onDirectInfoFound: {
                    OplusDirectFindResult _arg0 = data.readTypedObject(OplusDirectFindResult.CREATOR);
                    data.enforceNoDataAvail();
                    this.onDirectInfoFound(_arg0);
                    reply.writeNoException();
                    return true;
                }
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IOplusDirectFindCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public void onDirectInfoFound(OplusDirectFindResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedObject(result, 0);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_onDirectInfoFound,
                            _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
