package android.view;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Stub for OEM AIDL interface android.view.IOplusWindowStateObserver.
 * Hard-referenced (subclassed) by OppoGallery2 via the nested Stub type.
 * Faithfully mirrors the OOS AIDL shape (extends IInterface; abstract Stub
 * extends Binder) so class-load / verify of consumer subclasses succeeds.
 */
public interface IOplusWindowStateObserver extends IInterface {

    String DESCRIPTOR = "android.view.IOplusWindowStateObserver";

    void onWindowStateChange(Bundle bundle) throws RemoteException;

    /** No-op default impl, matching the OEM generated Default class. */
    class Default implements IOplusWindowStateObserver {
        @Override
        public void onWindowStateChange(Bundle bundle) throws RemoteException {}

        @Override
        public IBinder asBinder() {
            return null;
        }
    }

    /** Abstract binder Stub; concrete impl (e.g. in the gallery) supplies onWindowStateChange. */
    abstract class Stub extends Binder implements IOplusWindowStateObserver {

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static IOplusWindowStateObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin instanceof IOplusWindowStateObserver) {
                return (IOplusWindowStateObserver) iin;
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
            return super.onTransact(code, data, reply, flags);
        }
    }
}
