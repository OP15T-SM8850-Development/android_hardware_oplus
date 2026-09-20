package com.oplus.content;

import android.os.IInterface;
import android.os.RemoteException;

/** Stub companion of IOplusFeatureConfigManager (referenced in its descriptors). */
public interface IOplusFeatureActionObserver extends IInterface {

    String DESCRIPTOR = "com.oplus.content.IOplusFeatureActionObserver";

    void onFeaturesActionUpdate(String action, String actionValue, int featureID) throws RemoteException;
}
