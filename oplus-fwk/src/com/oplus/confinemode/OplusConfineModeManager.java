/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.confinemode;

import android.content.Context;

import java.util.List;

/**
 * Stub for the OEM confine-mode manager. Consumed by OppoGallery2 via its
 * com.heytap.addon.confinemode.OplusConfineModeManager wrapper, which calls
 * getInstance() and then invokes instance methods on the returned object, so
 * getInstance() must return a non-null singleton. getConfineMode() returns
 * OPLUS_CONFINE_MODE_NORMAL (0) meaning "no active confinement", the safe
 * non-restricting default. All mutators/observers are no-ops.
 */
public class OplusConfineModeManager {

    public static final int OPLUS_CONFINE_MODE_NORMAL = 0;
    public static final int OPLUS_CONFINE_MODE_DRIVE = 1;
    public static final int OPLUS_CONFINE_MODE_CHILDREN = 2;
    public static final int OPLUS_CONFINE_MODE_FOCUS = 4;
    public static final int OPLUS_CONFINE_MODE_GAME_FOCUS = 8;

    public static final int OPLUS_PERMIT_TYPE_CLEAR = 0;
    public static final int OPLUS_PERMIT_TYPE_REPLACE = 1;
    public static final int OPLUS_PERMIT_TYPE_APPEND = 2;
    public static final int OPLUS_PERMIT_TYPE_PKG = 4;
    public static final int OPLUS_PERMIT_TYPE_CPN = 8;

    private static OplusConfineModeManager sInstance = null;

    public static synchronized OplusConfineModeManager getInstance() {
        if (sInstance == null) {
            sInstance = new OplusConfineModeManager();
        }
        return sInstance;
    }

    public int getConfineMode() {
        return OPLUS_CONFINE_MODE_NORMAL;
    }

    public void setConfineMode(int mode, boolean enable) {
        // no-op
    }

    public void setPermitList(int mode, int permitType, List list, boolean sync) {
        // no-op
    }

    public boolean registerConfineModeObserver(Context context, ConfineModeObserver observer) {
        return false;
    }

    public boolean unregisterConfineModeObserver(Context context, ConfineModeObserver observer) {
        return false;
    }

    public interface ConfineModeObserver {
        void onChange(int mode, int state, int extra);
    }
}
