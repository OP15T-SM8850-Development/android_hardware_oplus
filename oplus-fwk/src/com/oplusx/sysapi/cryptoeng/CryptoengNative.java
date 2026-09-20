/*
 * Copyright (C) 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplusx.sysapi.cryptoeng;

import com.oplus.hardware.cryptoeng.CryptoEngManager;

/*
 * Stub of the oplusx sysapi cryptoeng shim — the modern entry point of the allawn
 * crypto SDK's SystemChannel (com.allawn.cryptography.teesdk in AIUnit 16.1.25);
 * the legacy sibling is com.oplus.os.OplusLockPatternUtils#cryptoEngCommand. Both
 * funnel into the same vendor.oplus.hardware.cryptoeng invoke command, so delegate
 * to our CryptoEngManager client.
 *
 * Descriptors mirrored from the OOS 16.0.8 consumers (AIUnit 16.1.25 smali):
 *   processCmdV2([B)[B (static)
 */
public class CryptoengNative {
    private CryptoengNative() {
    }

    public static byte[] processCmdV2(byte[] command) {
        return CryptoEngManager.getInstance().cryptoEngCommand(command);
    }
}
