package com.oplus.content;

import android.os.RemoteException;

import java.util.List;

/**
 * Stub of the OEM com.oplus.content.IOplusFeatureConfigManager.
 * In OOS this is a plain interface whose methods all carry default (no-op /
 * return-false) bodies; reproduced faithfully so any invoke-interface resolves.
 * FeatureID is provided here as the nested enum (binary name
 * com.oplus.content.IOplusFeatureConfigManager$FeatureID).
 */
public interface IOplusFeatureConfigManager {

    enum FeatureID {
        STATIC_COMPONENT,
        DYNAMIC_SIMSLOT_1,
        DYNAMIC_SIMSLOT_2,
        INVALID
    }

    default boolean hasFeature(String featureName) throws RemoteException {
        return false;
    }

    default boolean hasFeature(String name, FeatureID featureID) throws RemoteException {
        return false;
    }

    default boolean enableFeature(String featureName) throws RemoteException {
        return false;
    }

    default boolean enableFeature(String featureName, FeatureID featureID) throws RemoteException {
        return false;
    }

    default boolean disableFeature(String featureName) throws RemoteException {
        return false;
    }

    default boolean disableFeature(String featureName, FeatureID featureID) throws RemoteException {
        return false;
    }

    default void notifyFeaturesUpdate(String action, String actionValue) throws RemoteException {
    }

    default void notifyFeaturesUpdate(String action, String actionValue, FeatureID featureID) throws RemoteException {
    }

    default boolean registerFeatureObserver(List<String> features, IOplusFeatureObserver observer) {
        return false;
    }

    default boolean unregisterFeatureObserver(IOplusFeatureObserver observer) {
        return false;
    }

    default boolean registerFeatureObserver(List<String> features, FeatureID featureID, IOplusFeatureMapObserver observer) {
        return false;
    }

    default boolean unregisterFeatureObserver(FeatureID featureID, IOplusFeatureMapObserver observer) {
        return false;
    }

    default boolean registerFeatureActionObserver(IOplusFeatureActionObserver observer) {
        return false;
    }

    default boolean unregisterFeatureActionObserver(IOplusFeatureActionObserver observer) {
        return false;
    }
}
