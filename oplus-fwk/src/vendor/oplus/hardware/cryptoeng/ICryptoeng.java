/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 *
 * Client-side replica of the stable-VINTF AIDL classes shipped in the OEM
 * boot-classpath jar (oplus-framework.jar classes2.dex), interface hash
 * 765136eb397eb5b85ee1e089d8fec72c15e266b3, version 1.
 */

package vendor.oplus.hardware.cryptoeng;

public interface ICryptoeng extends android.os.IInterface {
    /** The version of this interface that the caller is built against. */
    public static final int VERSION = 1;
    public static final String HASH = "765136eb397eb5b85ee1e089d8fec72c15e266b3";

    /** Default implementation for ICryptoeng. */
    public static class Default implements vendor.oplus.hardware.cryptoeng.ICryptoeng {
        @Override
        public byte[] cryptoeng_invoke_command(byte[] in_buf) throws android.os.RemoteException {
            return null;
        }

        @Override
        public int getInterfaceVersion() {
            return 0;
        }

        @Override
        public String getInterfaceHash() {
            return "";
        }

        @Override
        public android.os.IBinder asBinder() {
            return null;
        }
    }

    /** Local-side IPC implementation stub class. */
    public static abstract class Stub extends android.os.Binder implements vendor.oplus.hardware.cryptoeng.ICryptoeng {
        /** Construct the stub and attach it to the interface. */
        public Stub() {
            this.markVintfStability();
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an vendor.oplus.hardware.cryptoeng.ICryptoeng
         * interface, generating a proxy if needed.
         */
        public static vendor.oplus.hardware.cryptoeng.ICryptoeng asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof vendor.oplus.hardware.cryptoeng.ICryptoeng))) {
                return ((vendor.oplus.hardware.cryptoeng.ICryptoeng) iin);
            }
            return new vendor.oplus.hardware.cryptoeng.ICryptoeng.Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        /** @hide */
        public static java.lang.String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case TRANSACTION_cryptoeng_invoke_command: {
                    return "cryptoeng_invoke_command";
                }
                case TRANSACTION_getInterfaceVersion: {
                    return "getInterfaceVersion";
                }
                case TRANSACTION_getInterfaceHash: {
                    return "getInterfaceHash";
                }
                default: {
                    return null;
                }
            }
        }

        /** @hide */
        public java.lang.String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags)
                throws android.os.RemoteException {
            java.lang.String descriptor = DESCRIPTOR;
            if (code >= android.os.IBinder.FIRST_CALL_TRANSACTION
                    && code <= android.os.IBinder.LAST_CALL_TRANSACTION) {
                data.enforceInterface(descriptor);
            }
            if (code == INTERFACE_TRANSACTION) {
                reply.writeString(descriptor);
                return true;
            } else if (code == TRANSACTION_getInterfaceVersion) {
                reply.writeNoException();
                reply.writeInt(getInterfaceVersion());
                return true;
            } else if (code == TRANSACTION_getInterfaceHash) {
                reply.writeNoException();
                reply.writeString(getInterfaceHash());
                return true;
            }
            switch (code) {
                case TRANSACTION_cryptoeng_invoke_command: {
                    byte[] _arg0;
                    _arg0 = data.createByteArray();
                    data.enforceNoDataAvail();
                    byte[] _result = this.cryptoeng_invoke_command(_arg0);
                    reply.writeNoException();
                    reply.writeByteArray(_result);
                    break;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
            return true;
        }

        private static class Proxy implements vendor.oplus.hardware.cryptoeng.ICryptoeng {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            private int mCachedVersion = -1;
            private String mCachedHash = "-1";

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public byte[] cryptoeng_invoke_command(byte[] in_buf) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain(asBinder());
                android.os.Parcel _reply = android.os.Parcel.obtain();
                byte[] _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(in_buf);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_cryptoeng_invoke_command, _data, _reply, 0);
                    if (!_status) {
                        throw new android.os.RemoteException("Method cryptoeng_invoke_command is unimplemented.");
                    }
                    _reply.readException();
                    _result = _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public int getInterfaceVersion() throws android.os.RemoteException {
                if (mCachedVersion == -1) {
                    android.os.Parcel data = android.os.Parcel.obtain(asBinder());
                    android.os.Parcel reply = android.os.Parcel.obtain();
                    try {
                        data.writeInterfaceToken(DESCRIPTOR);
                        boolean _status = mRemote.transact(Stub.TRANSACTION_getInterfaceVersion, data, reply, 0);
                        reply.readException();
                        mCachedVersion = reply.readInt();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return mCachedVersion;
            }

            @Override
            public synchronized String getInterfaceHash() throws android.os.RemoteException {
                if ("-1".equals(mCachedHash)) {
                    android.os.Parcel data = android.os.Parcel.obtain(asBinder());
                    android.os.Parcel reply = android.os.Parcel.obtain();
                    try {
                        data.writeInterfaceToken(DESCRIPTOR);
                        boolean _status = mRemote.transact(Stub.TRANSACTION_getInterfaceHash, data, reply, 0);
                        reply.readException();
                        mCachedHash = reply.readString();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return mCachedHash;
            }
        }

        static final int TRANSACTION_cryptoeng_invoke_command = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_getInterfaceVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16777214);
        static final int TRANSACTION_getInterfaceHash = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16777213);

        /** @hide */
        public int getMaxTransactionId() {
            return 16777214;
        }
    }

    public static final java.lang.String DESCRIPTOR = "vendor$oplus$hardware$cryptoeng$ICryptoeng".replace('$', '.');

    public byte[] cryptoeng_invoke_command(byte[] in_buf) throws android.os.RemoteException;

    public int getInterfaceVersion() throws android.os.RemoteException;

    public String getInterfaceHash() throws android.os.RemoteException;
}
