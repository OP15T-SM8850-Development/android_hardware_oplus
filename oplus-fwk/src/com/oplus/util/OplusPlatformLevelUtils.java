package com.oplus.util;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * LOS shim of the OEM com.oplus.util.OplusPlatformLevelUtils (OOS16.0.8 oplus-framework.jar).
 * The OEM derives sCpuLevel/sGpuLevel from oplus:array/{high,middle,low}_{cpu,gpu}_list keyed
 * on ro.soc.model. On infiniti OOS16.0.8 ro.soc.model=SM8850 appears in NONE of the lists
 * (they end at SM8650) and no RRO extends them, so the stock device computes -1 (LEVEL_UNDEF)
 * for both; the level-getter fallbacks then land on LEVEL_HIGH. The constants below reproduce
 * the exact stock values without the resource machinery.
 */
public class OplusPlatformLevelUtils {
    private static final String TAG = "OplusPlatformLevelUtils";
    private static final long GB = 0x40000000L;

    public static final int LEVEL_UNDEF = -1;
    public static final int LEVEL_LOW = 1;
    public static final int LEVEL_MIDDLE = 2;
    public static final int LEVEL_HIGH = 3;
    public static final int LEVEL_TYPE_FOR_RAM = 1;
    public static final int LEVEL_TYPE_FOR_CPU = 2;
    public static final int LEVEL_TYPE_FOR_GPU = 3;

    private static final int THRESHOLD_LOW_RAM = 4;
    private static final int THRESHOLD_HIGH_RAM = 12;

    public static final boolean IS_LIGHT_OS =
            SystemProperties.getBoolean("ro.oplus.lightos", false);
    public static final int TOTAL_RAM = obtainRam();

    private static final int ANIMATION_LEVEL =
            SystemProperties.getInt("ro.oplus.animationlevel", 0);
    private static final int GAUSSIAN_LEVEL =
            SystemProperties.getInt("ro.oplus.gaussianlevel", 0);

    // Stock-computed values on infiniti OOS16.0.8 (SM8850 absent from the res arrays).
    private static final int sRamLevel = initPlatformRamLevel();
    private static final int sCpuLevel = LEVEL_UNDEF;
    private static final int sGpuLevel = LEVEL_UNDEF;

    private static volatile OplusPlatformLevelUtils sInstance = null;

    private OplusPlatformLevelUtils(Context context) {
        if (context == null) {
            Log.e(TAG, "OplusPlatformLevelUtils mContext is null!");
        }
    }

    public static OplusPlatformLevelUtils getInstance(Context context) {
        if (sInstance == null) {
            synchronized (OplusPlatformLevelUtils.class) {
                if (sInstance == null) {
                    sInstance = new OplusPlatformLevelUtils(context);
                }
            }
        }
        return sInstance;
    }

    /** OEM source line 301-308. Stock OOS16.0.8 on infiniti: RAM->3, CPU->-1, GPU->-1. */
    public int getPlatformLevel(int type) {
        if (type == LEVEL_TYPE_FOR_RAM) {
            return sRamLevel;
        }
        if (type == LEVEL_TYPE_FOR_CPU) {
            return sCpuLevel;
        }
        if (type == LEVEL_TYPE_FOR_GPU) {
            return sGpuLevel;
        }
        return LEVEL_UNDEF;
    }

    /** OEM source line 322-331. Stock OOS16.0.8 on infiniti returns LEVEL_HIGH (3). */
    public int getPlatformAnimationLevel() {
        if (ANIMATION_LEVEL >= LEVEL_LOW && ANIMATION_LEVEL <= LEVEL_HIGH) {
            return ANIMATION_LEVEL;
        }
        if (IS_LIGHT_OS) {
            return LEVEL_LOW;
        }
        if (sGpuLevel == LEVEL_MIDDLE || sGpuLevel == LEVEL_HIGH) {
            return sGpuLevel;
        }
        return LEVEL_HIGH;
    }

    /** OEM source line 340-347. Stock OOS16.0.8 on infiniti returns LEVEL_HIGH (3). */
    public int getPlatformGaussianLevel() {
        if (GAUSSIAN_LEVEL >= LEVEL_LOW && GAUSSIAN_LEVEL <= LEVEL_HIGH) {
            return GAUSSIAN_LEVEL;
        }
        if (sGpuLevel != LEVEL_UNDEF) {
            return sGpuLevel;
        }
        return LEVEL_HIGH;
    }

    /** OEM initPlatformRamLevel, source line 251-257. 12/16 GB SKUs -> LEVEL_HIGH. */
    private static int initPlatformRamLevel() {
        if (TOTAL_RAM <= THRESHOLD_LOW_RAM) {
            return LEVEL_LOW;
        }
        if (TOTAL_RAM >= THRESHOLD_HIGH_RAM) {
            return LEVEL_HIGH;
        }
        return LEVEL_MIDDLE;
    }

    /** OEM obtainRam, source line 238-247 (verbatim, incl. reflection + GiB round-up). */
    private static int obtainRam() {
        try {
            Class<?> clazz = Class.forName("android.os.Process");
            Method method = clazz.getMethod("getTotalMemory");
            long totalMemory = (Long) method.invoke(null);
            long totalPhysicalMemory = (totalMemory + GB - 1) & ~(GB - 1);
            return (int) (totalPhysicalMemory / 1024 / 1024 / 1024);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return -1;
        }
    }
}
