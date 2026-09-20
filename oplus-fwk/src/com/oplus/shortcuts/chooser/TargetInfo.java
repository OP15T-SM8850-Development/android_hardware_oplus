package com.oplus.shortcuts.chooser;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;

/**
 * Minimal closure stub for the OEM chooser TargetInfo contract.
 * Consumers (OppoGallery2) invoke these via invoke-interface; every instance
 * method is a default returning a safe non-crashing value, and
 * prepareIntentForCrossProfileLaunch is a static no-op (invoke-static in OEM dex).
 */
public interface TargetInfo {

    default ComponentName getChooserTargetComponentName() {
        return null;
    }

    default ShortcutInfo getDirectShareShortcutInfo() {
        return null;
    }

    default CharSequence getDisplayLabel() {
        return null;
    }

    default float getModifiedScore() {
        return 0f;
    }

    default ResolveInfo getResolveInfo() {
        return null;
    }

    default Intent getTargetIntent() {
        return null;
    }

    default boolean isPinned() {
        return false;
    }

    default boolean isSimilar(TargetInfo other) {
        return false;
    }

    static void prepareIntentForCrossProfileLaunch(Activity activity, Intent intent, int userId) {
        // no-op
    }
}
