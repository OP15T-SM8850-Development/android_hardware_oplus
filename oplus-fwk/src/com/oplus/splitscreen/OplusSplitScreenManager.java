/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.splitscreen;

import com.oplus.app.IOplusSplitScreenObserver;

public class OplusSplitScreenManager {
    private static volatile OplusSplitScreenManager sInstance = null;

    private OplusSplitScreenManager() {
    }

    public static OplusSplitScreenManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusSplitScreenManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusSplitScreenManager();
                }
            }
        }
        return sInstance;
    }

    public boolean isInSplitScreenMode() {
        return false;
    }

    public boolean registerSplitScreenObserver(IOplusSplitScreenObserver observer) {
        return false;
    }

    public boolean unregisterSplitScreenObserver(IOplusSplitScreenObserver observer) {
        return false;
    }
}
