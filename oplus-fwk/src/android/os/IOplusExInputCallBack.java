package android.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.view.InputEvent;

/**
 * Class-closure stub for the OEM oplus-framework AIDL interface.
 * Referenced by OppoGallery2: com.heytap.addon.os.IOplusExInputCallBack$Stub$1
 * extends android.os.IOplusExInputCallBack$Stub and invokes its no-arg <init>.
 * Real OOS kind: public interface abstract, implements IInterface.
 */
public interface IOplusExInputCallBack extends IInterface {

    public static final String DESCRIPTOR = "android.os.IOplusExInputCallBack";

    public void onInputEvent(InputEvent event) throws RemoteException;

    public static abstract class Stub extends Binder implements IOplusExInputCallBack {

        public Stub() {
            super();
        }

        public static IOplusExInputCallBack asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin instanceof IOplusExInputCallBack) {
                return (IOplusExInputCallBack) iin;
            }
            return null;
        }

        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
