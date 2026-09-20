package com.oplus.shortcuts;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ShortcutInfo;
import android.os.AsyncTask;
import android.os.UserHandle;
import android.service.chooser.ChooserTarget;

import com.oplus.shortcuts.chooser.DisplayResolveInfo;
import com.oplus.wrapper.app.prediction.AppPredictor;
import com.oplus.wrapper.app.prediction.AppTarget;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * LOS shim of the OEM com.oplus.shortcuts.ShortcutLoader (OOS16.0.8 oplus-framework.jar).
 * The OEM loader posts exactly one Result per queryShortcuts() call through the callback
 * on the main executor — even when zero share shortcuts match — which is what lets the
 * OppoGallery2 share sheet resolve its direct-share strip. This shim has no shortcut
 * backend (no OplusLauncherAppsManager), so it always reports the OEM zero-match shape:
 * Result(false, <requested appTargets>, new ShortcutResultInfo[0], fresh HashMaps).
 * OppoGallery2 only ever calls the public constructor and queryShortcuts(); the predictor
 * it passes is always null because our AppPredictorFactory.create() returns null.
 */
public class ShortcutLoader {

    private static final Request NO_REQUEST = new Request(new DisplayResolveInfo[0]);

    private final AtomicReference<Consumer<Result>> mCallback = new AtomicReference<>();
    private final AtomicReference<Request> mActiveRequest = new AtomicReference<>(NO_REQUEST);
    private final IntentFilter mTargetIntentFilter;
    private final Executor mBackgroundExecutor;
    private final Executor mCallbackExecutor;

    public ShortcutLoader(Context context, AppPredictor appPredictor, UserHandle userHandle,
            IntentFilter targetIntentFilter, Consumer<Result> callback) {
        // OEM: this(context, appPredictor, userHandle, /* isPersonalProfile */ true,
        //         targetIntentFilter, AsyncTask.SERIAL_EXECUTOR, context.getMainExecutor(), callback)
        mTargetIntentFilter = targetIntentFilter;
        mBackgroundExecutor = AsyncTask.SERIAL_EXECUTOR;
        mCallbackExecutor = context.getMainExecutor();
        mCallback.set(callback);
        // OEM registers AppPredictor prediction updates here when appPredictor != null.
        // Our AppPredictorFactory stub always yields null (and the wrapper AppPredictor
        // stub is memberless), so there is nothing to register.
    }

    public void queryShortcuts(DisplayResolveInfo[] appTargets) {
        // OEM ShortcutLoader.queryShortcuts (source line 171-176)
        if (isDestroyed()) {
            return;
        }
        mActiveRequest.set(new Request(appTargets));
        mBackgroundExecutor.execute(this::loadShortcuts);
    }

    /** OEM public surface (source line 150-155); OppoGallery2 does not call it today. */
    public void destroy() {
        mCallback.getAndSet(null);
    }

    private boolean isDestroyed() {
        return mCallback.get() == null;
    }

    private void loadShortcuts() {
        // OEM queryDirectShareTargets() bails without reporting only when the intent
        // filter is null; in every other (predictor-less) case it queries the share
        // targets and ALWAYS posts a Result — empty when nothing matches. The shim
        // reports that stock zero-match shape directly.
        if (mTargetIntentFilter == null) {
            return;
        }
        DisplayResolveInfo[] appTargets = mActiveRequest.get().appTargets;
        postReport(new Result(false, appTargets, new ShortcutResultInfo[0],
                new HashMap<>(), new HashMap<>()));
    }

    private void postReport(Result result) {
        // OEM postReport (source line 298): always delivered via the callback executor
        // (main thread), never synchronously from queryShortcuts.
        mCallbackExecutor.execute(() -> report(result));
    }

    private void report(Result result) {
        // OEM report (source line 303-307)
        Consumer<Result> callback = mCallback.get();
        if (callback != null) {
            callback.accept(result);
        }
    }

    private static class Request {
        private final DisplayResolveInfo[] appTargets;

        Request(DisplayResolveInfo[] targets) {
            appTargets = targets;
        }
    }

    public static class Result {
        public final boolean isFromAppPredictor;
        public final DisplayResolveInfo[] appTargets;
        public final ShortcutResultInfo[] shortcutsByApp;
        public final Map<ChooserTarget, AppTarget> directShareAppTargetCache;
        public final Map<ChooserTarget, ShortcutInfo> directShareShortcutInfoCache;

        public Result(boolean isFromAppPredictor, DisplayResolveInfo[] appTargets,
                ShortcutResultInfo[] shortcutsByApp,
                Map<ChooserTarget, AppTarget> directShareAppTargetCache,
                Map<ChooserTarget, ShortcutInfo> directShareShortcutInfoCache) {
            this.isFromAppPredictor = isFromAppPredictor;
            this.appTargets = appTargets;
            this.shortcutsByApp = shortcutsByApp;
            this.directShareAppTargetCache = directShareAppTargetCache;
            this.directShareShortcutInfoCache = directShareShortcutInfoCache;
        }
        // NOTE: no hashCode() override — the OEM Result has none; OppoGallery2 calls
        // result.hashCode() (fs6 data-class hashCode) and stock resolves Object.hashCode().
    }

    public static class ShortcutResultInfo {
        public final DisplayResolveInfo appTarget;
        public final List<ChooserTarget> shortcuts;

        public ShortcutResultInfo(DisplayResolveInfo appTarget, List<ChooserTarget> shortcuts) {
            this.appTarget = appTarget;
            this.shortcuts = shortcuts;
        }
    }
}
