/*
 * Copyright (C) 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.ota;

/*
 * Stub -- the OOS OTA update record. LineageOS never reports an OOS system
 * update, so consumers see "no update" values.
 *
 * Descriptors mirrored from the OOS 16.0.8 consumers (RomUpdate 16.0.11 smali):
 *   getUpdateType()I
 *   isUpdateSucc()Z
 */
public class OplusSystemUpdateInfo {
    public int getUpdateType() {
        return 0;
    }

    public boolean isUpdateSucc() {
        return false;
    }
}
