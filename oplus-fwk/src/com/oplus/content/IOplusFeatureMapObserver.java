package com.oplus.content;

import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/** Stub companion of IOplusFeatureConfigManager (referenced in its descriptors). */
public interface IOplusFeatureMapObserver extends IInterface {

    String DESCRIPTOR = "com.oplus.content.IOplusFeatureMapObserver";

    void onFeatureUpdate(List<String> list, int featureID) throws RemoteException;
}
