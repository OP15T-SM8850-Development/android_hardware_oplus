package com.oplus.shortcuts.chooser;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Icon;
import android.os.Bundle;

import com.oplus.wrapper.app.prediction.AppTarget;

/**
 * Closure stub. Consumers create instances only through the static
 * newSelectableTargetInfo factory and read the three getters below; the rest of
 * the TargetInfo contract is inherited as safe defaults via ChooserTargetInfo.
 */
public final class SelectableTargetInfo extends ChooserTargetInfo {

    public static TargetInfo newSelectableTargetInfo(DisplayResolveInfo sourceInfo, ResolveInfo backupResolveInfo,
            Intent resolvedIntent, ComponentName chooserTargetComponentName, CharSequence chooserTargetUnsanitizedTitle,
            Icon chooserTargetIcon, Bundle chooserTargetIntentExtras, float modifiedScore, ShortcutInfo shortcutInfo,
            AppTarget appTarget, Intent referrerFillInIntent) {
        return new SelectableTargetInfo();
    }

    @Override
    public ComponentName getChooserTargetComponentName() {
        return null;
    }

    public Bundle getChooserTargetIntentExtras() {
        return null;
    }

    @Override
    public Intent getTargetIntent() {
        return null;
    }
}
