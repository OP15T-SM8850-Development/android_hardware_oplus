package com.oplus.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IOplusAppStartController extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusAppStartController";

    void appStartMonitor(String pkg, String activity, String action, String extra1, String extra2) throws RemoteException;

    void preventStartMonitor(String pkg, String activity, String action, String extra1, String extra2) throws RemoteException;

    void notifyPreventIndulge(String pkg) throws RemoteException;

    public static class Default implements IOplusAppStartController {
        @Override // com.oplus.app.IOplusAppStartController
        public void appStartMonitor(String pkg, String activity, String action, String extra1, String extra2) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusAppStartController
        public void preventStartMonitor(String pkg, String activity, String action, String extra1, String extra2) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusAppStartController
        public void notifyPreventIndulge(String pkg) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOplusAppStartController {
        static final int TRANSACTION_appStartMonitor = 1;
        static final int TRANSACTION_preventStartMonitor = 2;
        static final int TRANSACTION_notifyPreventIndulge = 3;

        public Stub() {
            attachInterface(this, IOplusAppStartController.DESCRIPTOR);
        }

        public static IOplusAppStartController asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusAppStartController.DESCRIPTOR);
            if (iin != null && (iin instanceof IOplusAppStartController)) {
                return (IOplusAppStartController) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "appStartMonitor";
                case 2:
                    return "preventStartMonitor";
                case 3:
                    return "notifyPreventIndulge";
                default:
                    return null;
            }
        }

        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        public int getMaxTransactionId() {
            return 2;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= 1 && code <= 16777215) {
                data.enforceInterface(IOplusAppStartController.DESCRIPTOR);
            }
            if (code == 1598968902) {
                reply.writeString(IOplusAppStartController.DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1: {
                    String _arg0 = data.readString();
                    String _arg1 = data.readString();
                    String _arg2 = data.readString();
                    String _arg3 = data.readString();
                    String _arg4 = data.readString();
                    data.enforceNoDataAvail();
                    appStartMonitor(_arg0, _arg1, _arg2, _arg3, _arg4);
                    return true;
                }
                case 2: {
                    String _arg0 = data.readString();
                    String _arg1 = data.readString();
                    String _arg2 = data.readString();
                    String _arg3 = data.readString();
                    String _arg4 = data.readString();
                    data.enforceNoDataAvail();
                    preventStartMonitor(_arg0, _arg1, _arg2, _arg3, _arg4);
                    return true;
                }
                case 3: {
                    String _arg0 = data.readString();
                    data.enforceNoDataAvail();
                    notifyPreventIndulge(_arg0);
                    return true;
                }
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IOplusAppStartController {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusAppStartController.DESCRIPTOR;
            }

            @Override // com.oplus.app.IOplusAppStartController
            public void appStartMonitor(String pkg, String activity, String action, String extra1, String extra2) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IOplusAppStartController.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeString(activity);
                    _data.writeString(action);
                    _data.writeString(extra1);
                    _data.writeString(extra2);
                    this.mRemote.transact(1, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAppStartController
            public void preventStartMonitor(String pkg, String activity, String action, String extra1, String extra2) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IOplusAppStartController.DESCRIPTOR);
                    _data.writeString(pkg);
                    _data.writeString(activity);
                    _data.writeString(action);
                    _data.writeString(extra1);
                    _data.writeString(extra2);
                    this.mRemote.transact(2, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAppStartController
            public void notifyPreventIndulge(String pkg) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(IOplusAppStartController.DESCRIPTOR);
                    _data.writeString(pkg);
                    this.mRemote.transact(3, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }
    }
}
