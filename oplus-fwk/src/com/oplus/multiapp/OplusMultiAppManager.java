/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.multiapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Stub for the OEM multi-app (parallel/cloned apps) manager.
 * Hard-referenced by OppoGallery2 via getInstance() + instance getters.
 * Multi-app is not a feature we enable, so getters return neutral, empty
 * defaults (no clones) rather than crashing.
 */
public class OplusMultiAppManager extends BaseOplusMultiAppManager {
    private static OplusMultiAppManager sInstance = null;

    public static OplusMultiAppManager getInstance() {
        if (sInstance == null) {
            sInstance = new OplusMultiAppManager();
        }
        return sInstance;
    }

    public String getMultiAppAlias(String packageName) {
        return null;
    }

    public List getMultiAppList(int userId) {
        return new ArrayList();
    }
}
