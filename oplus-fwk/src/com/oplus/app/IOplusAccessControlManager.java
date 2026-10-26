package com.oplus.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface IOplusAccessControlManager extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusAccessControlManager";

    void setAccessControlAppsInfo(String type, Map accessControlInfo, int userId) throws RemoteException;

    Map getAccessControlAppsInfo(String type, int userId) throws RemoteException;

    void setAccessControlEnabled(String type, boolean enable, int userId) throws RemoteException;

    boolean getAccessControlEnabled(String type, int userId) throws RemoteException;

    void addEncryptPass(String packageName, int windowMode, int userId) throws RemoteException;

    boolean isEncryptPass(String packageName, int userId) throws RemoteException;

    boolean isEncryptedPackage(String packageName, int userId) throws RemoteException;

    boolean registerAccessControlObserver(String type, IOplusAccessControlObserver observer) throws RemoteException;

    boolean unregisterAccessControlObserver(String type, IOplusAccessControlObserver observer) throws RemoteException;

    void updateRusList(int type, List<String> addList, List<String> deleteList) throws RemoteException;

    public static class Default implements IOplusAccessControlManager {
        @Override // com.oplus.app.IOplusAccessControlManager
        public void setAccessControlAppsInfo(String type, Map accessControlInfo, int userId) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusAccessControlManager
        public Map getAccessControlAppsInfo(String type, int userId) throws RemoteException {
            return null;
        }

        @Override // com.oplus.app.IOplusAccessControlManager
        public void setAccessControlEnabled(String type, boolean enable, int userId) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusAccessControlManager
        public boolean getAccessControlEnabled(String type, int userId) throws RemoteException {
            return false;
        }

        @Override // com.oplus.app.IOplusAccessControlManager
        public void addEncryptPass(String packageName, int windowMode, int userId) throws RemoteException {
        }

        @Override // com.oplus.app.IOplusAccessControlManager
        public boolean isEncryptPass(String packageName, int userId) throws RemoteException {
            return false;
        }

        @Override // com.oplus.app.IOplusAccessControlManager
        public boolean isEncryptedPackage(String packageName, int userId) throws RemoteException {
            return false;
        }

        @Override // com.oplus.app.IOplusAccessControlManager
        public boolean registerAccessControlObserver(String type, IOplusAccessControlObserver observer) throws RemoteException {
            return false;
        }

        @Override // com.oplus.app.IOplusAccessControlManager
        public boolean unregisterAccessControlObserver(String type, IOplusAccessControlObserver observer) throws RemoteException {
            return false;
        }

        @Override // com.oplus.app.IOplusAccessControlManager
        public void updateRusList(int type, List<String> addList, List<String> deleteList) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOplusAccessControlManager {
        static final int TRANSACTION_setAccessControlAppsInfo = 1;
        static final int TRANSACTION_getAccessControlAppsInfo = 2;
        static final int TRANSACTION_setAccessControlEnabled = 3;
        static final int TRANSACTION_getAccessControlEnabled = 4;
        static final int TRANSACTION_addEncryptPass = 5;
        static final int TRANSACTION_isEncryptPass = 6;
        static final int TRANSACTION_isEncryptedPackage = 7;
        static final int TRANSACTION_registerAccessControlObserver = 8;
        static final int TRANSACTION_unregisterAccessControlObserver = 9;
        static final int TRANSACTION_updateRusList = 10;

        public Stub() {
            attachInterface(this, IOplusAccessControlManager.DESCRIPTOR);
        }

        public static IOplusAccessControlManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(IOplusAccessControlManager.DESCRIPTOR);
            if (iin != null && (iin instanceof IOplusAccessControlManager)) {
                return (IOplusAccessControlManager) iin;
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
                    return "setAccessControlAppsInfo";
                case 2:
                    return "getAccessControlAppsInfo";
                case 3:
                    return "setAccessControlEnabled";
                case 4:
                    return "getAccessControlEnabled";
                case 5:
                    return "addEncryptPass";
                case 6:
                    return "isEncryptPass";
                case 7:
                    return "isEncryptedPackage";
                case 8:
                    return "registerAccessControlObserver";
                case 9:
                    return "unregisterAccessControlObserver";
                case 10:
                    return "updateRusList";
                default:
                    return null;
            }
        }

        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        public int getMaxTransactionId() {
            return 9;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= 1 && code <= 16777215) {
                data.enforceInterface(IOplusAccessControlManager.DESCRIPTOR);
            }
            if (code == 1598968902) {
                reply.writeString(IOplusAccessControlManager.DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1: {
                    String _arg0 = data.readString();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _arg1 = data.readHashMap(cl);
                    int _arg2 = data.readInt();
                    data.enforceNoDataAvail();
                    setAccessControlAppsInfo(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case 2: {
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    data.enforceNoDataAvail();
                    Map _result = getAccessControlAppsInfo(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeMap(_result);
                    return true;
                }
                case 3: {
                    String _arg0 = data.readString();
                    boolean _arg1 = data.readBoolean();
                    int _arg2 = data.readInt();
                    data.enforceNoDataAvail();
                    setAccessControlEnabled(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case 4: {
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    data.enforceNoDataAvail();
                    boolean _result = getAccessControlEnabled(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeBoolean(_result);
                    return true;
                }
                case 5: {
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    data.enforceNoDataAvail();
                    addEncryptPass(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case 6: {
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    data.enforceNoDataAvail();
                    boolean _result = isEncryptPass(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeBoolean(_result);
                    return true;
                }
                case 7: {
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    data.enforceNoDataAvail();
                    boolean _result = isEncryptedPackage(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeBoolean(_result);
                    return true;
                }
                case 8: {
                    String _arg0 = data.readString();
                    IOplusAccessControlObserver _arg1 =
                            IOplusAccessControlObserver.Stub.asInterface(data.readStrongBinder());
                    data.enforceNoDataAvail();
                    boolean _result = registerAccessControlObserver(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeBoolean(_result);
                    return true;
                }
                case 9: {
                    String _arg0 = data.readString();
                    IOplusAccessControlObserver _arg1 =
                            IOplusAccessControlObserver.Stub.asInterface(data.readStrongBinder());
                    data.enforceNoDataAvail();
                    boolean _result = unregisterAccessControlObserver(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeBoolean(_result);
                    return true;
                }
                case 10: {
                    int _arg0 = data.readInt();
                    List<String> _arg1 = data.createStringArrayList();
                    List<String> _arg2 = data.createStringArrayList();
                    data.enforceNoDataAvail();
                    updateRusList(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IOplusAccessControlManager {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOplusAccessControlManager.DESCRIPTOR;
            }

            @Override // com.oplus.app.IOplusAccessControlManager
            public void setAccessControlAppsInfo(String type, Map accessControlInfo, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAccessControlManager.DESCRIPTOR);
                    _data.writeString(type);
                    _data.writeMap(accessControlInfo);
                    _data.writeInt(userId);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAccessControlManager
            public Map getAccessControlAppsInfo(String type, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAccessControlManager.DESCRIPTOR);
                    _data.writeString(type);
                    _data.writeInt(userId);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    HashMap _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAccessControlManager
            public void setAccessControlEnabled(String type, boolean enable, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAccessControlManager.DESCRIPTOR);
                    _data.writeString(type);
                    _data.writeBoolean(enable);
                    _data.writeInt(userId);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAccessControlManager
            public boolean getAccessControlEnabled(String type, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAccessControlManager.DESCRIPTOR);
                    _data.writeString(type);
                    _data.writeInt(userId);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readBoolean();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAccessControlManager
            public void addEncryptPass(String packageName, int windowMode, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAccessControlManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(windowMode);
                    _data.writeInt(userId);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAccessControlManager
            public boolean isEncryptPass(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAccessControlManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readBoolean();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAccessControlManager
            public boolean isEncryptedPackage(String packageName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAccessControlManager.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeInt(userId);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readBoolean();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAccessControlManager
            public boolean registerAccessControlObserver(String type, IOplusAccessControlObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAccessControlManager.DESCRIPTOR);
                    _data.writeString(type);
                    _data.writeStrongInterface(observer);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readBoolean();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAccessControlManager
            public boolean unregisterAccessControlObserver(String type, IOplusAccessControlObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAccessControlManager.DESCRIPTOR);
                    _data.writeString(type);
                    _data.writeStrongInterface(observer);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    boolean _result = _reply.readBoolean();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oplus.app.IOplusAccessControlManager
            public void updateRusList(int type, List<String> addList, List<String> deleteList) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(IOplusAccessControlManager.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeStringList(addList);
                    _data.writeStringList(deleteList);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
