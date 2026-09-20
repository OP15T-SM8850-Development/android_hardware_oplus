/*
 * Copyright (C) 2024 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 * Reconstructed from OOS 16.0.8 oplus-framework.jar (com.oplus.media.MediaFile).
 * Faithful port of the OEM extension/MIME classifier that OppoGallery2 hard-references.
 * The OEM used libcore.content.type.MimeMap; we use the public android.webkit.MimeTypeMap
 * (semantically equivalent, always on the boot classpath) for normalize/guess.
 */

package com.oplus.media;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class MediaFile {
    private static final String TAG = "MediaFile";

    public static final int FILE_TYPE_MP3 = 0x1;
    public static final int FILE_TYPE_M4A = 0x2;
    public static final int FILE_TYPE_WAV = 0x3;
    public static final int FILE_TYPE_AMR = 0x4;
    public static final int FILE_TYPE_AWB = 0x5;
    public static final int FILE_TYPE_WMA = 0x6;
    public static final int FILE_TYPE_OGG = 0x7;
    public static final int FILE_TYPE_AAC = 0x8;
    public static final int FILE_TYPE_MKA = 0x9;
    public static final int FILE_TYPE_FLAC = 0xa;
    public static final int FILE_TYPE_AUDIO_3GPP = 0xb;
    public static final int FILE_TYPE_MID = 0xb;
    public static final int FILE_TYPE_SMF = 0xc;
    public static final int FILE_TYPE_IMY = 0xd;
    public static final int FILE_TYPE_MP4 = 0x15;
    public static final int FILE_TYPE_M4V = 0x16;
    public static final int FILE_TYPE_3GPP = 0x17;
    public static final int FILE_TYPE_3GPP2 = 0x18;
    public static final int FILE_TYPE_WMV = 0x19;
    public static final int FILE_TYPE_ASF = 0x1a;
    public static final int FILE_TYPE_MKV = 0x1b;
    public static final int FILE_TYPE_MP2TS = 0x1c;
    public static final int FILE_TYPE_AVI = 0x1d;
    public static final int FILE_TYPE_WEBM = 0x1e;
    public static final int FILE_TYPE_JPEG = 0x1f;
    public static final int FILE_TYPE_GIF = 0x20;
    public static final int FILE_TYPE_PNG = 0x21;
    public static final int FILE_TYPE_BMP = 0x22;
    public static final int FILE_TYPE_WBMP = 0x23;
    public static final int FILE_TYPE_WEBP = 0x24;
    public static final int FILE_TYPE_HEIF = 0x25;
    public static final int FILE_TYPE_M3U = 0x29;
    public static final int FILE_TYPE_PLS = 0x2a;
    public static final int FILE_TYPE_WPL = 0x2b;
    public static final int FILE_TYPE_HTTPLIVE = 0x2c;
    public static final int FILE_TYPE_FL = 0x33;
    public static final int FILE_TYPE_TEXT = 0x64;
    public static final int FILE_TYPE_HTML = 0x65;
    public static final int FILE_TYPE_PDF = 0x66;
    public static final int FILE_TYPE_XML = 0x67;
    public static final int FILE_TYPE_MS_WORD = 0x68;
    public static final int FILE_TYPE_MS_EXCEL = 0x69;
    public static final int FILE_TYPE_MS_POWERPOINT = 0x6a;
    public static final int FILE_TYPE_ZIP = 0x6b;
    public static final int FILE_TYPE_MP2PS = 0xc8;
    public static final int FILE_TYPE_QT = 0xc9;
    public static final int FILE_TYPE_DNG = 0x12c;
    public static final int FILE_TYPE_CR2 = 0x12d;
    public static final int FILE_TYPE_NEF = 0x12e;
    public static final int FILE_TYPE_NRW = 0x12f;
    public static final int FILE_TYPE_ARW = 0x130;
    public static final int FILE_TYPE_RW2 = 0x131;
    public static final int FILE_TYPE_ORF = 0x132;
    public static final int FILE_TYPE_RAF = 0x133;
    public static final int FILE_TYPE_PEF = 0x134;
    public static final int FILE_TYPE_SRW = 0x135;
    public static final int FILE_TYPE_APE = 0x3e9;
    public static final int FILE_TYPE_MP2 = 0x3ea;
    public static final int FILE_TYPE_CUE = 0x3eb;
    public static final int FILE_TYPE_RA = 0x3ec;
    public static final int FILE_TYPE_FLV = 0x44d;
    public static final int FILE_TYPE_RV = 0x44e;
    public static final int FILE_TYPE_MOV = 0x44f;
    public static final int FILE_TYPE_M2TS = 0x450;
    public static final int FILE_TYPE_RAR = 0x2711;
    public static final int FILE_TYPE_JAR = 0x2712;
    public static final int FILE_TYPE_APK = 0x271b;
    public static final int FILE_TYPE_CHM = 0x2725;
    public static final int FILE_TYPE_CSV = 0x2726;
    public static final int FILE_TYPE_ICS = 0x2727;
    public static final int FILE_TYPE_VCF = 0x2728;
    public static final int FILE_TYPE_VCS = 0x2729;
    public static final int FILE_TYPE_EBK = 0x272a;
    public static final int FILE_TYPE_EPUB = 0x272b;

    public static final int MEDIA_TYPE_COMPRESS = 0x2711;
    public static final int MEDIA_TYPE_APK = 0x2712;
    public static final int MEDIA_TYPE_DOC = 0x2713;

    public static final int SCAN_ALL_FILE = 0x0;
    public static final int SCAN_AUDIO_FILE = 0x1;
    public static final int SCAN_IMAGE_FILE = 0x2;
    public static final int SCAN_VIDEO_FILE = 0x4;
    public static final int SCAN_OTHER_FILE = 0x8;

    public static final String OPLUS_DEFAULT_ALARM = "oplus_customize_default_alarm";
    public static final String OPLUS_DEFAULT_NOTIFICATION = "oplus_customize_default_notification";
    public static final String OPLUS_DEFAULT_NOTIFICATION_SIM2 = "oplus_customize_default_notification_sim2";
    public static final String OPLUS_DEFAULT_RINGTONE = "oplus_customize_default_ringtone";
    public static final String OPLUS_DEFAULT_RINGTONE_SIM2 = "oplus_customize_default_ringtone_sim2";
    public static final String OPLUS_DEFAULT_SMS_NOTIFICATION = "oplus_customize_default_sms_notification_sound";

    private static final String DEFAULT_MIME_TYPE = "application/octet-stream";

    private static final HashMap<String, MediaFileType> sFileTypeMap = new HashMap<>();
    private static final HashMap<String, Integer> sMimeTypeMap = new HashMap<>();
    private static final HashMap<Integer, String> sFormatToMimeTypeMap = new HashMap<>();

    private static final HashSet<String> sDocMimeTypes = new HashSet<>();
    private static final HashSet<String> sRawImageMimeTypes = new HashSet<>();

    public MediaFile() {
    }

    private static void add(String extension, int fileType, String mimeType) {
        sFileTypeMap.put(extension, new MediaFileType(fileType, mimeType));
        sMimeTypeMap.put(mimeType, Integer.valueOf(fileType));
    }

    private static void putFmt(int formatCode, String mimeType) {
        if (!sFormatToMimeTypeMap.containsKey(Integer.valueOf(formatCode))) {
            sFormatToMimeTypeMap.put(Integer.valueOf(formatCode), mimeType);
        }
    }

    static {
        add("MP3", 0x1, "audio/mpeg");
        add("MPGA", 0x1, "audio/mpeg");
        add("M4A", 0x2, "audio/mp4");
        add("WAV", 0x3, "audio/x-wav");
        add("AMR", 0x4, "audio/amr");
        add("3GPP", 0xb, "audio/3gpp");
        add("AWB", 0x5, "audio/amr-wb");
        add("WMA", 0x6, "audio/x-ms-wma");
        add("OGG", 0x7, "audio/ogg");
        add("OGG", 0x7, "application/ogg");
        add("OGA", 0x7, "application/ogg");
        add("AAC", 0x8, "audio/aac");
        add("AAC", 0x8, "audio/aac-adts");
        add("MKA", 0x9, "audio/x-matroska");
        add("MID", 0xb, "audio/mid");
        add("MID", 0xb, "audio/midi");
        add("MIDI", 0xb, "audio/midi");
        add("XMF", 0xb, "audio/midi");
        add("RTTTL", 0xb, "audio/midi");
        add("SMF", 0xc, "audio/sp-midi");
        add("IMY", 0xd, "audio/imelody");
        add("RTX", 0xb, "audio/midi");
        add("OTA", 0xb, "audio/midi");
        add("MXMF", 0xb, "audio/midi");
        add("MPEG", 0x15, "video/mpeg");
        add("MPG", 0x15, "video/mpeg");
        add("MP4", 0x15, "video/mp4");
        add("M4V", 0x16, "video/mp4");
        add("MOV", 0xc9, "video/quicktime");
        add("3GP", 0x17, "video/3gpp");
        add("3GPP", 0x17, "video/3gpp");
        add("3G2", 0x18, "video/3gpp2");
        add("3GPP2", 0x18, "video/3gpp2");
        add("MKV", 0x1b, "video/x-matroska");
        add("WEBM", 0x1e, "video/webm");
        add("TS", 0x1c, "video/mp2ts");
        add("AVI", 0x1d, "video/avi");
        add("WMV", 0x19, "video/x-ms-wmv");
        add("ASF", 0x1a, "video/x-ms-asf");
        add("JPG", 0x1f, "image/jpeg");
        add("JPEG", 0x1f, "image/jpeg");
        add("GIF", 0x20, "image/gif");
        add("PNG", 0x21, "image/png");
        add("BMP", 0x22, "image/x-ms-bmp");
        add("BMP", 0x22, "image/bmp");
        add("WBMP", 0x23, "image/vnd.wap.wbmp");
        add("WEBP", 0x24, "image/webp");
        add("HEIC", 0x25, "image/heif");
        add("HEIF", 0x25, "image/heif");
        add("DNG", 0x12c, "image/x-adobe-dng");
        add("CR2", 0x12d, "image/x-canon-cr2");
        add("NEF", 0x12e, "image/x-nikon-nef");
        add("NRW", 0x12f, "image/x-nikon-nrw");
        add("ARW", 0x130, "image/x-sony-arw");
        add("RW2", 0x131, "image/x-panasonic-rw2");
        add("ORF", 0x132, "image/x-olympus-orf");
        add("RAF", 0x133, "image/x-fuji-raf");
        add("PEF", 0x134, "image/x-pentax-pef");
        add("SRW", 0x135, "image/x-samsung-srw");
        add("M3U", 0x29, "audio/x-mpegurl");
        add("M3U", 0x29, "application/x-mpegurl");
        add("PLS", 0x2a, "audio/x-scpls");
        add("WPL", 0x2b, "application/vnd.ms-wpl");
        add("M3U8", 0x2c, "application/vnd.apple.mpegurl");
        add("M3U8", 0x2c, "audio/mpegurl");
        add("M3U8", 0x2c, "audio/x-mpegurl");
        add("FL", 0x33, "application/x-android-drm-fl");
        add("TXT", 0x64, "text/plain");
        add("HTM", 0x65, "text/html");
        add("HTML", 0x65, "text/html");
        add("PDF", 0x66, "application/pdf");
        add("DOC", 0x68, "application/msword");
        add("XLS", 0x69, "application/vnd.ms-excel");
        add("PPT", 0x6a, "application/mspowerpoint");
        add("FLAC", 0xa, "audio/flac");
        add("ZIP", 0x6b, "application/zip");
        add("MPG", 0xc8, "video/mp2p");
        add("MPEG", 0xc8, "video/mp2p");
        add("APE", 0x3e9, "audio/ape");
        add("MP2", 0x3ea, "audio/mpeg");
        add("CUE", 0x3eb, "audio/cue");
        add("FLV", 0x44d, "video/x-flv");
        add("F4V", 0x44d, "video/x-flv");
        add("MOV", 0x44f, "video/x-quicktime");
        add("M2TS", 0x450, "video/m2ts");
        add("DOCX", 0x68, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        add("XLSX", 0x69, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        add("PPTX", 0x6a, "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        add("RAR", 0x2711, "application/rar");
        add("JAR", 0x2712, "application/java-archive");
        add("APK", 0x271b, "application/vnd.android.package-archive");
        add("CHM", 0x2725, "application/x-expandedbook");
        add("CSV", 0x2726, "text/comma-separated-values");
        add("ICS", 0x2727, "text/calendar");
        add("VCF", 0x2728, "text/x-vcard");
        add("VCS", 0x2729, "text/x-vcalendar");
        add("EBK2", 0x272a, "text/x-expandedbook");
        add("EBK3", 0x272a, "text/x-expandedbook");
        add("EPUB", 0x272b, "text/plain");

        putFmt(0x3009, "audio/mpeg");
        putFmt(0x3008, "audio/x-wav");
        putFmt(0x300b, "audio/mp4");
        putFmt(0x3007, "audio/x-aiff");
        putFmt(0x300a, "video/avi");
        putFmt(0x3801, "image/jpeg");
        putFmt(0x3807, "image/gif");
        putFmt(0x380b, "image/png");
        putFmt(0x3804, "image/x-ms-bmp");
        putFmt(0x3800, "image/vnd.wap.wbmp");
        putFmt(0x3812, "image/heif");
        putFmt(0x3811, "image/x-adobe-dng");
        putFmt(0x3802, "image/x-nikon-nef");
        putFmt(0x380d, "image/x-canon-cr2");

        sDocMimeTypes.add("text/csv");
        sDocMimeTypes.add("chemical/x-chemdraw");
        sDocMimeTypes.add("application/msword");
        sDocMimeTypes.add("text/plain");
        sDocMimeTypes.add("text/x-vcard");
        sDocMimeTypes.add("text/x-vcalendar");
        sDocMimeTypes.add("application/vnd.ms-excel");
        sDocMimeTypes.add("text/calendar");
        sDocMimeTypes.add("text/comma-separated-values");
        sDocMimeTypes.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        sDocMimeTypes.add("application/vnd.ms-powerpoint");
        sDocMimeTypes.add("application/vnd.openxmlformats-officedocument.presentationml.presentation");
        sDocMimeTypes.add("text/html");
        sDocMimeTypes.add("application/pdf");
        sDocMimeTypes.add("application/epub+zip");
        sDocMimeTypes.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        sRawImageMimeTypes.add("image/x-canon-cr2");
        sRawImageMimeTypes.add("image/x-nikon-nrw");
        sRawImageMimeTypes.add("image/x-nikon-nef");
        sRawImageMimeTypes.add("image/x-olympus-orf");
        sRawImageMimeTypes.add("image/x-pentax-pef");
        sRawImageMimeTypes.add("image/x-panasonic-rw2");
        sRawImageMimeTypes.add("image/x-adobe-dng");
        sRawImageMimeTypes.add("image/tiff");
        sRawImageMimeTypes.add("image/x-sony-arw");
        sRawImageMimeTypes.add("image/x-samsung-srw");
    }

    public static boolean isAudioFileType(int fileType) {
        return (fileType >= 0x1 && fileType <= 0xd)
                || (fileType >= 0x3e9 && fileType <= 0x3ec);
    }

    public static boolean isVideoFileType(int fileType) {
        return (fileType >= 0x15 && fileType <= 0x1e)
                || (fileType >= 0xc8 && fileType <= 0xc9)
                || (fileType >= 0x44d && fileType <= 0x450);
    }

    public static boolean isImageFileType(int fileType) {
        return (fileType >= 0x1f && fileType <= 0x25)
                || (fileType >= 0x12c && fileType <= 0x135);
    }

    public static boolean isRawImageFileType(int fileType) {
        return fileType >= 0x12c && fileType <= 0x135;
    }

    public static boolean isDrmFileType(int fileType) {
        return fileType == 0x33;
    }

    public static boolean isCompressFileType(int fileType) {
        return (fileType >= 0x2711 && fileType <= 0x2712) || fileType == 0x6b;
    }

    public static boolean isDocFileType(int fileType) {
        if (fileType >= 0x2725 && fileType <= 0x272b) {
            return true;
        }
        return fileType >= 0x64 && fileType <= 0x6a && fileType != 0x67;
    }

    public static boolean isApkFileType(int fileType) {
        return fileType == 0x271b;
    }

    private static String normalizeMimeType(String mimeType) {
        if (mimeType == null) {
            return DEFAULT_MIME_TYPE;
        }
        MimeTypeMap mimeMap = MimeTypeMap.getSingleton();
        String extension = mimeMap.getExtensionFromMimeType(mimeType);
        if (extension != null) {
            String extensionMimeType = mimeMap.getMimeTypeFromExtension(extension);
            if (extensionMimeType != null) {
                return extensionMimeType;
            }
        }
        return mimeType;
    }

    public static boolean isAudioMimeType(String mimeType) {
        return normalizeMimeType(mimeType).startsWith("audio/");
    }

    public static boolean isVideoMimeType(String mimeType) {
        return normalizeMimeType(mimeType).startsWith("video/");
    }

    public static boolean isImageMimeType(String mimeType) {
        return normalizeMimeType(mimeType).startsWith("image/");
    }

    public static boolean isApkMimeType(String mimeType) {
        return "application/vnd.android.package-archive".equals(normalizeMimeType(mimeType));
    }

    public static boolean isDocMimeType(String mimeType) {
        return sDocMimeTypes.contains(normalizeMimeType(mimeType));
    }

    public static boolean isRawImageMimeType(String mimeType) {
        return sRawImageMimeTypes.contains(normalizeMimeType(mimeType));
    }

    public static MediaFileType getFileType(String path) {
        int lastDot = path.lastIndexOf(0x2e);
        if (lastDot < 0) {
            return null;
        }
        return sFileTypeMap.get(path.substring(lastDot + 1).toUpperCase(Locale.ROOT));
    }

    public static int getFileTypeForMimeType(String mimeType) {
        Integer value = sMimeTypeMap.get(mimeType);
        return value == null ? 0 : value.intValue();
    }

    public static String getFileExtension(String path) {
        if (path == null) {
            return null;
        }
        int lastDot = path.lastIndexOf(0x2e);
        if (lastDot >= 0) {
            return path.substring(lastDot + 1);
        }
        return null;
    }

    private static String guessMimeTypeFromExtension(String extension) {
        String mimeType = null;
        if (extension != null) {
            MimeTypeMap mimeMap = MimeTypeMap.getSingleton();
            mimeType = mimeMap.getMimeTypeFromExtension(extension);
            if (mimeType != null) {
                if ("audio/x-pn-realaudio".equals(mimeType)
                        || "audio/x-pn-realaudio-plugin".equals(mimeType)) {
                    return null;
                }
                return mimeType;
            }
            String lower = extension.toLowerCase(Locale.ROOT);
            switch (lower) {
                case "cue":
                    mimeType = "audio/cue";
                    break;
                case "f4v":
                    mimeType = "video/x-flv";
                    break;
                case "m2ts":
                    mimeType = "video/m2ts";
                    break;
                case "ebk2":
                case "ebk3":
                    mimeType = "text/x-expandedbook";
                    break;
                default:
                    break;
            }
        }
        return mimeType != null ? mimeType : DEFAULT_MIME_TYPE;
    }

    public static String getMimeTypeForFile(String path) {
        String mimeType = guessMimeTypeFromExtension(getFileExtension(path));
        return mimeType != null ? mimeType : DEFAULT_MIME_TYPE;
    }

    public static String getMimeTypeForFormatCode(int formatCode) {
        String mimeType = sFormatToMimeTypeMap.get(Integer.valueOf(formatCode));
        return mimeType != null ? mimeType : DEFAULT_MIME_TYPE;
    }

    public static String getMimeType(String path, int formatCode) {
        String mimeType = getMimeTypeForFile(path);
        if (!DEFAULT_MIME_TYPE.equals(mimeType)) {
            return mimeType;
        }
        return getMimeTypeForFormatCode(formatCode);
    }

    private static Uri getUriFor(Context context, String name) {
        ContentResolver resolver = context.getContentResolver();
        String value = Settings.System.getString(resolver, name);
        if (value != null) {
            return Uri.parse(value);
        }
        Log.e(TAG, name + " not set?!!!");
        return null;
    }

    public static Uri getDefaultAlarmUri(Context context) {
        return getUriFor(context, OPLUS_DEFAULT_ALARM);
    }

    public static Uri getDefaultNotificationUri(Context context) {
        return getUriFor(context, OPLUS_DEFAULT_NOTIFICATION);
    }

    public static Uri getDefaultNotificationUriSIM2(Context context) {
        return getUriFor(context, OPLUS_DEFAULT_NOTIFICATION_SIM2);
    }

    public static Uri getDefaultRingtoneUri(Context context) {
        return getUriFor(context, OPLUS_DEFAULT_RINGTONE);
    }

    public static Uri getDefaultRingtoneUriSIM2(Context context) {
        return getUriFor(context, OPLUS_DEFAULT_RINGTONE_SIM2);
    }

    public static Uri getDefaultSmsNotificationUri(Context context) {
        return getUriFor(context, OPLUS_DEFAULT_SMS_NOTIFICATION);
    }

    public static class MediaFileType {
        public final int fileType;
        public final String mimeType;

        MediaFileType(int fileType, String mimeType) {
            this.fileType = fileType;
            this.mimeType = mimeType;
        }
    }
}
