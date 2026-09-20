package android.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/**
 * Class-closure stub for the OEM oplus-framework AIDL interface.
 * Referenced by OppoGallery2 (com.heytap.addon.os.IOplusExService wrapper):
 *   invoke-interface registerInputEvent / registerRawInputEvent / unregisterInputEvent
 *   invoke-static    IOplusExService$Stub.asInterface(IBinder)
 * Real OOS kind: public interface abstract, implements IInterface.
 * Only the consumer-referenced methods are declared to keep the descriptor set
 * closed; asInterface always returns a non-crashing no-op impl so the gallery
 * never NPEs when the OEM binder service is absent on this build.
 */
public interface IOplusExService extends IInterface {

    public static final String DESCRIPTOR = "android.os.IOplusExService";

    public boolean registerInputEvent(IOplusExInputCallBack callback) throws RemoteException;

    public boolean registerRawInputEvent(IOplusExInputCallBack callback) throws RemoteException;

    public void unregisterInputEvent(IOplusExInputCallBack callback) throws RemoteException;

    public static class Default implements IOplusExService {
        @Override
        public boolean registerInputEvent(IOplusExInputCallBack callback) {
            return false;
        }
        @Override
        public boolean registerRawInputEvent(IOplusExInputCallBack callback) {
            return false;
        }
        @Override
        public void unregisterInputEvent(IOplusExInputCallBack callback) {
        }
        @Override
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOplusExService {

        public Stub() {
            super();
        }

        public static IOplusExService asInterface(IBinder obj) {
            if (obj != null) {
                IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
                if (iin instanceof IOplusExService) {
                    return (IOplusExService) iin;
                }
            }
            return new Default();
        }

        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
