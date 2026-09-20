package com.oplus.content;

import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/** Stub companion of IOplusFeatureConfigManager (referenced in its descriptors). */
public interface IOplusFeatureObserver extends IInterface {

    String DESCRIPTOR = "com.oplus.content.IOplusFeatureObserver";

    void onFeatureUpdate(List<String> features) throws RemoteException;
}
