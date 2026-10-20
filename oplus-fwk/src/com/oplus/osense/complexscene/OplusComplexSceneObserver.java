/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.osense.complexscene;

import java.util.List;

/**
 * Observer for osense "complex scene" changes (e.g. floating windows). The
 * OnePlus camera subclasses it and registers it through OplusActivityManager.
 * There is no complex scene service here, so it never gets called back.
 */
public class OplusComplexSceneObserver {
    public OplusComplexSceneObserver() {
    }

    public void onChanged(int scene, List list) {
    }
}
