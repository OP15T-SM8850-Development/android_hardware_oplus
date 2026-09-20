/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.touchnode;

public class OplusTouchNodeManager {
    public static final int DOUBLE_TAP_ENABLE_NODE = 0x1;
    public static final int COORDINATE_NODE = 0x2;
    public static final int BASELINE_TEST_NODE = 0x3;
    public static final int CALIBRATION_NODE = 0x4;
    public static final int OPLUS_TP_DIRECTION_NODE = 0x5;
    public static final int OPLUS_TP_LIMIT_WHITELIST_NODE = 0x6;
    public static final int OPLUS_TP_LIMIT_ENABLE_NODE = 0x7;
    public static final int KERNEL_GRIP_HANDLE_NODE = 0x8;
    public static final int REPORT_RATE_WHITE_LIST_NODE = 0x9;
    public static final int CHARGE_DETECT_NODE = 0xa;
    public static final int WIRELESS_CHARGE_DETECT_NODE = 0xb;
    public static final int HEAD_SET_DETECT_NODE = 0xc;
    public static final int BLACK_SCREEN_TEST_NODE = 0xd;
    public static final int BASELINE_RESULT_NODE = 0xe;
    public static final int BLACK_SCREEN_RESULT_NODE = 0xf;
    public static final int TP_AGING_TEST_NODE = 0x10;
    public static final int DEBUG_DELTA_NODE = 0x11;
    public static final int DEBUG_BASELINE_NODE = 0x12;
    public static final int HOVER_SELFDATA_NODE = 0x13;
    public static final int DEBUG_HEALTH_MONITOR_NODE = 0x14;
    public static final int DOUBLE_TAP_INDEP_NODE = 0x15;
    public static final int TOUCH_OPTIMIZED_TIME_NODE = 0x16;

    private static volatile OplusTouchNodeManager sInstance = null;

    private OplusTouchNodeManager() {
    }

    public static OplusTouchNodeManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusTouchNodeManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusTouchNodeManager();
                }
            }
        }
        return sInstance;
    }

    public boolean writeNodeFile(int node, String value) {
        return false;
    }

    public boolean writeNodeFileByDevice(int device, int node, String value) {
        return false;
    }
}
