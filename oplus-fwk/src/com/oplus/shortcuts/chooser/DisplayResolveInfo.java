package com.oplus.shortcuts.chooser;

import android.content.Intent;
import android.content.pm.ResolveInfo;

/**
 * Closure stub. Consumers create instances only through the static
 * newDisplayResolveInfo factory and read getResolvedIntent(); all TargetInfo
 * methods are inherited as safe defaults from the interface.
 */
public class DisplayResolveInfo implements TargetInfo {

    public static DisplayResolveInfo newDisplayResolveInfo(Intent originalIntent, ResolveInfo resolveInfo,
            CharSequence displayLabel, CharSequence extendedInfo, Intent resolvedIntent) {
        return new DisplayResolveInfo();
    }

    public Intent getResolvedIntent() {
        return null;
    }
}
