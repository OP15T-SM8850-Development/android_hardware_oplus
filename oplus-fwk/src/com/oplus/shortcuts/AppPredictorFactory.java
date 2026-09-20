package com.oplus.shortcuts;

import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;

import com.oplus.wrapper.app.prediction.AppPredictor;

/**
 * Closure stub. Constructed by OppoGallery2, whose create() result is passed
 * straight into ShortcutLoader (a no-op here), so returning null is safe.
 */
public class AppPredictorFactory {

    public AppPredictorFactory(Context context, String sharedText, IntentFilter targetIntentFilter,
            boolean appPredictionAvailable) {
    }

    public AppPredictor create(UserHandle userHandle) {
        return null;
    }
}
