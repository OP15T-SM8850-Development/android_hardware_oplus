/*
 * Copyright (C) 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.os;

import com.oplus.hardware.cryptoeng.CryptoEngManager;

/*
 * OOS routes OplusLockPatternUtils.cryptoEngCommand() into the cryptoeng TEE HAL
 * (vendor.oplus.hardware.cryptoeng). The allawn crypto SDK compiled into AIUnit
 * (com.allawn.cryptography.teesdk.SystemChannel) uses it as the legacy entry point
 * into cryptoeng for device attestation / key commands — the sibling path is
 * com.oplusx.sysapi.cryptoeng.CryptoengNative. Delegate to our CryptoEngManager
 * client; it returns null when the vendor service is unreachable, which the SDK
 * already treats as "cryptoeng unavailable" (same as its NoClassDefFound fallback).
 *
 * Descriptors mirrored from the OOS 16.0.8 consumers (AIUnit 16.1.25 smali):
 *   <init>()V
 *   cryptoEngCommand([B)[B
 */
public class OplusLockPatternUtils {
    public OplusLockPatternUtils() {
    }

    public byte[] cryptoEngCommand(byte[] command) {
        return CryptoEngManager.getInstance().cryptoEngCommand(command);
    }
}
