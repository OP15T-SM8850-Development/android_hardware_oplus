package com.oplus.deepthinker;

import com.oplus.deepthinker.sdk.aidl.proton.deepsleep.TotalPredictResult;
import com.oplus.eventhub.sdk.aidl.EventRequestConfig;
import com.oplus.eventhub.sdk.aidl.IEventCallback;

/**
 * Minimal stub of the OEM boot-classpath interface
 * com.oplus.deepthinker.IOplusDeepThinkerManager.
 *
 * Consumer: OppoGallery2 (via its com.heytap.addon.deepthinker wrapper) hard-references
 * exactly the three members below. Methods are declared {@code default} with safe returns
 * so that (a) invoke-interface resolves at verify time and (b) no AbstractMethodError can
 * arise from any partial impl / dynamic proxy the wrapper may use.
 *
 * The real interface additionally extends android.common.IOplusCommonFeature and declares
 * ~30 other members; none of those are referenced by our shipped consumers, so they are
 * intentionally omitted (a plain interface loads without pulling in unreferenced companions).
 *
 * IEventCallback / EventRequestConfig are provided by the com.oplus.eventhub package group.
 */
public interface IOplusDeepThinkerManager {

    default TotalPredictResult getDeepSleepTotalPredictResult() {
        return null;
    }

    default boolean registerCallback(IEventCallback callback, EventRequestConfig config) {
        return false;
    }

    default boolean unregisterCallback(IEventCallback callback) {
        return false;
    }
}
