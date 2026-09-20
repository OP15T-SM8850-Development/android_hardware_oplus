/*
 * Copyright (C) 2026 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.media;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public final class OplusHeifConverterBridgeTest {
    public static void main(String[] args) {
        byte[] jpegMagic = {(byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xe0};
        ByteArrayOutputStream converted = new ByteArrayOutputStream();
        boolean result = OplusHeifConverterBridge.convert(new ByteArrayInputStream(new byte[8]),
                95, converted, (input, decode, quality, output, scratch) -> {
                    output.write(jpegMagic);
                    return true;
                });
        check(result, "conversion backend result");
        byte[] output = converted.toByteArray();
        check(output.length >= 3 && output[0] == (byte) 0xff && output[1] == (byte) 0xd8
                && output[2] == (byte) 0xff, "JPEG magic");

        ByteArrayOutputStream unavailable = new ByteArrayOutputStream();
        check(!OplusHeifConverterBridge.convert(new ByteArrayInputStream(new byte[8]), 95,
                unavailable, null), "native-load fallback");
        check(unavailable.size() == 0, "fallback output remains empty");

        check(!OplusHeifConverterBridge.convert(new ByteArrayInputStream(new byte[8]), 95,
                new ByteArrayOutputStream(), (input, decode, quality, stream, scratch) -> {
                    throw new UnsatisfiedLinkError("test");
                }), "late native-link fallback");
    }

    private static void check(boolean value, String message) {
        if (!value) {
            throw new AssertionError(message);
        }
    }
}
