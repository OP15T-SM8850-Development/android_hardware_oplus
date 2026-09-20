/*
 * Copyright (C) 2024 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.media;

import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.util.Log;
import android.view.Surface;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class OplusHeifConverter {
    private static final int DECODE_BUFFER_SIZE = 16 * 1024;
    private static final int HEADER_BUFFER_SIZE = 1024;
    private static final long MAX_SIZE = 100L * 1024 * 1024;
    private static final int BOX_FTYP = 0x66747970;
    private static final int BRAND_HEIC = 0x68656963;
    private static final int BRAND_MIF1 = 0x6d696631;
    private static final String TAG = "OplusHeifConverter_Java";

    private static final boolean NATIVE_AVAILABLE;

    private long m10BitObject;
    private Surface mSurface;

    static {
        boolean loaded = false;
        try {
            System.loadLibrary("oplus_heifconverter");
            loaded = true;
        } catch (LinkageError | SecurityException error) {
            Log.w(TAG, "OEM HEIF converter unavailable; using safe fallback", error);
        }
        NATIVE_AVAILABLE = loaded;
    }

    private static native boolean nativeCheckPPS(byte[] data);
    private static native long nativeCreateDecoder();
    private static native boolean nativeDecode(long decoder, InputStream input, Surface surface,
            int sampleSize);
    private static native boolean nativeDecodeRegion(long decoder, InputStream input, int left,
            int top, int width, int height, Surface surface, int sampleSize, int wholeImage);
    private static native void nativeDestroyDecoder(long decoder);
    private static native HeifDecodedFrame nativeGetDecodeFrame(long decoder, InputStream input,
            int sampleSize, boolean directBuffer);
    private static native HeifDecodedFrame nativeGetRegionDecodeFrame(long decoder,
            InputStream input, int left, int top, int width, int height, int sampleSize,
            boolean directBuffer, int wholeImage);
    private static native boolean nativeHeifConvert(InputStream input, byte[] decodeBuffer,
            int quality, OutputStream output, byte[] scratchBuffer);
    private static native void nativeRecycle(long bufferId, long subBufferId);
    private static native boolean nativeRender(byte[] data, int width, int height, Surface surface,
            int dataSpace);
    private static native boolean nativeRenderDirectBuffer(long bufferId, long subBufferId,
            int width, int height, Surface surface, int dataSpace);

    public OplusHeifConverter() {
    }

    public static boolean convertHeifToJpegFromPath(String path, int quality,
            OutputStream output) {
        if (path == null) {
            return false;
        }
        try (InputStream input = new FileInputStream(path)) {
            return convertHeifToJpegFromStream(input, quality, output);
        } catch (IOException | SecurityException error) {
            Log.e(TAG, "Unable to convert HEIF path", error);
            return false;
        }
    }

    public static boolean convertHeifToJpegFromStream(InputStream input, int quality,
            OutputStream output) {
        OplusHeifConverterBridge.Backend backend =
                NATIVE_AVAILABLE ? OplusHeifConverter::nativeHeifConvert : null;
        return OplusHeifConverterBridge.convert(input, quality, output, backend);
    }

    public boolean createDecoder() {
        if (!NATIVE_AVAILABLE) {
            return false;
        }
        try {
            m10BitObject = nativeCreateDecoder();
            return m10BitObject != 0;
        } catch (LinkageError | RuntimeException error) {
            Log.e(TAG, "Unable to create HEIF decoder", error);
            return false;
        }
    }

    public boolean destroyDecoder() {
        if (!NATIVE_AVAILABLE || m10BitObject == 0) {
            return false;
        }
        try {
            nativeDestroyDecoder(m10BitObject);
            m10BitObject = 0;
            return true;
        } catch (LinkageError | RuntimeException error) {
            Log.e(TAG, "Unable to destroy HEIF decoder", error);
            return false;
        }
    }

    public HeifDecodedFrame decode(FileDescriptor fd, int sampleSize, boolean directBuffer) {
        if (!NATIVE_AVAILABLE || fd == null) {
            return null;
        }
        try (InputStream input = new FileInputStream(fd)) {
            return decode(input, sampleSize, directBuffer);
        } catch (IOException | RuntimeException error) {
            Log.e(TAG, "Unable to decode HEIF descriptor", error);
            return null;
        }
    }

    public HeifDecodedFrame decode(InputStream input, int sampleSize, boolean directBuffer) {
        if (!NATIVE_AVAILABLE || input == null) {
            return null;
        }
        try {
            HeifDecodedFrame frame = nativeGetDecodeFrame(m10BitObject, input, sampleSize,
                    directBuffer);
            if (frame != null) {
                frame.m_recycled = false;
            }
            return frame;
        } catch (LinkageError | RuntimeException error) {
            Log.e(TAG, "Unable to decode HEIF stream", error);
            return null;
        }
    }

    public boolean decode(FileDescriptor fd, int sampleSize, Surface surface) {
        if (!NATIVE_AVAILABLE || fd == null) {
            return false;
        }
        try (InputStream input = new FileInputStream(fd)) {
            return decode(input, sampleSize, surface);
        } catch (IOException | RuntimeException error) {
            Log.e(TAG, "Unable to render HEIF descriptor", error);
            return false;
        }
    }

    public boolean decode(InputStream input, int sampleSize, Surface surface) {
        if (!NATIVE_AVAILABLE || input == null) {
            return false;
        }
        mSurface = surface;
        try {
            return nativeDecode(m10BitObject, input, surface, sampleSize);
        } catch (LinkageError | RuntimeException error) {
            Log.e(TAG, "Unable to render HEIF stream", error);
            return false;
        }
    }

    public HeifDecodedFrame decodeRegion(FileDescriptor fd, Rect region, int sampleSize,
            boolean directBuffer) {
        if (!NATIVE_AVAILABLE || fd == null) {
            return null;
        }
        try (InputStream input = new FileInputStream(fd)) {
            return decodeRegion(input, region, sampleSize, directBuffer);
        } catch (IOException | RuntimeException error) {
            Log.e(TAG, "Unable to decode HEIF region descriptor", error);
            return null;
        }
    }

    public HeifDecodedFrame decodeRegion(InputStream input, Rect region, int sampleSize,
            boolean directBuffer) {
        if (!NATIVE_AVAILABLE || input == null) {
            return null;
        }
        Rect target = region != null ? region : new Rect();
        int wholeImage = region == null ? 1 : 0;
        try {
            HeifDecodedFrame frame = nativeGetRegionDecodeFrame(m10BitObject, input, target.left,
                    target.top, target.width(), target.height(), sampleSize, directBuffer,
                    wholeImage);
            if (frame != null) {
                frame.m_recycled = false;
            }
            return frame;
        } catch (LinkageError | RuntimeException error) {
            Log.e(TAG, "Unable to decode HEIF region", error);
            return null;
        }
    }

    public boolean decodeRegion(FileDescriptor fd, Rect region, int sampleSize, Surface surface) {
        if (!NATIVE_AVAILABLE || fd == null) {
            return false;
        }
        try (InputStream input = new FileInputStream(fd)) {
            return decodeRegion(input, region, sampleSize, surface);
        } catch (IOException | RuntimeException error) {
            Log.e(TAG, "Unable to render HEIF region descriptor", error);
            return false;
        }
    }

    public boolean decodeRegion(InputStream input, Rect region, int sampleSize, Surface surface) {
        if (!NATIVE_AVAILABLE || input == null) {
            return false;
        }
        mSurface = surface;
        Rect target = region != null ? region : new Rect();
        int wholeImage = region == null ? 1 : 0;
        try {
            return nativeDecodeRegion(m10BitObject, input, target.left, target.top, target.width(),
                    target.height(), surface, sampleSize, wholeImage);
        } catch (LinkageError | RuntimeException error) {
            Log.e(TAG, "Unable to render HEIF region", error);
            return false;
        }
    }

    public int getFormat(FileDescriptor fd) throws IOException {
        try (InputStream input = new FileInputStream(fd)) {
            return getFormat(input);
        }
    }

    public int getFormat(InputStream input) throws IOException {
        if (input == null) {
            return 0;
        }
        int available = input.available();
        if (available >= MAX_SIZE) {
            return 0;
        }
        ByteArrayOutputStream data = new ByteArrayOutputStream(Math.max(available, 1024));
        byte[] chunk = new byte[DECODE_BUFFER_SIZE];
        int total = 0;
        for (int read; (read = input.read(chunk)) != -1;) {
            total += read;
            if (total >= MAX_SIZE) {
                return 0;
            }
            data.write(chunk, 0, read);
        }
        byte[] bytes = data.toByteArray();
        for (int i = 0; i + 20 < bytes.length; i++) {
            if (byteArrayToInt(bytes, i) == 0x68766343) {
                return ((bytes[i + 17] & 3) == 2 && (bytes[i + 18] & 3) == 2) ? 1 : 0;
            }
        }
        return 0;
    }

    public boolean isHEIFFile(InputStream input) {
        if (input == null) {
            return false;
        }
        byte[] data = new byte[HEADER_BUFFER_SIZE];
        try {
            int read = new BufferedInputStream(input).read(data);
            if (read <= 12 || byteArrayToInt(data, 4) != BOX_FTYP) {
                return false;
            }
            int boxSize = byteArrayToInt(data, 0);
            if (boxSize <= 8 || boxSize > read) {
                return false;
            }
            Set<Integer> brands = new HashSet<>();
            brands.add(BRAND_HEIC);
            brands.add(BRAND_MIF1);
            for (int offset = 8; offset + 4 <= boxSize; offset += 4) {
                brands.remove(byteArrayToInt(data, offset));
            }
            return brands.isEmpty();
        } catch (IOException | RuntimeException error) {
            return false;
        }
    }

    public int byteArrayToInt(byte[] data, int offset) {
        return ((data[offset] & 0xff) << 24) | ((data[offset + 1] & 0xff) << 16)
                | ((data[offset + 2] & 0xff) << 8) | (data[offset + 3] & 0xff);
    }

    public static class HeifDecodedFrame {
        public ColorSpace m_ColorSpace;
        public long m_buffer_id;
        public long m_buffer_id_sub;
        public int m_frame_height;
        public int m_frame_width;
        public boolean m_recycled;
        public byte[] m_yuvdata;

        public HeifDecodedFrame(byte[] yuv, int width, int height, long id) {
            m_yuvdata = yuv;
            m_frame_width = width;
            m_frame_height = height;
            m_buffer_id = id;
        }

        public final boolean isRecycled() {
            return m_recycled;
        }

        public void recycle() {
            if (m_recycled) {
                return;
            }
            if (NATIVE_AVAILABLE) {
                try {
                    nativeRecycle(m_buffer_id, m_buffer_id_sub);
                } catch (LinkageError | RuntimeException error) {
                    Log.e(TAG, "Unable to recycle HEIF frame", error);
                }
            }
            m_yuvdata = null;
            m_recycled = true;
        }

        public boolean render(Surface surface, boolean directBuffer) {
            return render(surface, directBuffer, ColorSpace.get(ColorSpace.Named.DISPLAY_P3));
        }

        public boolean render(Surface surface, boolean directBuffer, ColorSpace colorSpace) {
            if (!NATIVE_AVAILABLE || m_recycled) {
                return m_recycled;
            }
            int dataSpace = colorSpace != null
                    && colorSpace.getId() != ColorSpace.Named.DISPLAY_P3.ordinal()
                    ? 142671872 : 143261696;
            try {
                return directBuffer
                        ? nativeRenderDirectBuffer(m_buffer_id, m_buffer_id_sub, m_frame_width,
                                m_frame_height, surface, dataSpace)
                        : nativeRender(m_yuvdata, m_frame_width, m_frame_height, surface, dataSpace);
            } catch (LinkageError | RuntimeException error) {
                Log.e(TAG, "Unable to render HEIF frame", error);
                return false;
            }
        }
    }
}
