/*
 * Copyright (C) 2026 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.media;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class OplusHeifConverterBridge {
    private static final int DECODE_BUFFER_SIZE = 16 * 1024;
    private static final int SCRATCH_BUFFER_SIZE = 4 * 1024;

    interface Backend {
        boolean convert(InputStream input, byte[] decodeBuffer, int quality, OutputStream output,
                byte[] scratchBuffer) throws IOException;
    }

    private OplusHeifConverterBridge() {
    }

    static boolean convert(InputStream input, int quality, OutputStream output, Backend backend) {
        if (output == null) {
            throw new NullPointerException("output");
        }
        if (quality < 0 || quality > 100) {
            throw new IllegalArgumentException("quality must be 0..100");
        }
        if (input == null || backend == null) {
            return false;
        }
        try {
            return backend.convert(input, new byte[DECODE_BUFFER_SIZE], quality, output,
                    new byte[SCRATCH_BUFFER_SIZE]);
        } catch (IOException | LinkageError | RuntimeException error) {
            return false;
        }
    }
}
