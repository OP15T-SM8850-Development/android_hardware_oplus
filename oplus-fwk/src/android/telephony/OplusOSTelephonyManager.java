/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package android.telephony;

import android.content.Context;

import java.util.Collections;
import java.util.List;

/**
 * Stub for the OEM android.telephony.OplusOSTelephonyManager.
 * Public surface ported faithfully from OOS16 oplus-framework.jar; all bodies
 * are safe non-crashing no-ops. getDefault() returns a real singleton so
 * consumers (OppoGallery2) never NPE on chained calls.
 */
public class OplusOSTelephonyManager {

    // Radio Access Family constants (values from OOS oplus-framework.jar).
    public static final int RAF_UNKNOWN = 0x0;
    public static final int RAF_GPRS = 0x1;
    public static final int RAF_EDGE = 0x2;
    public static final int RAF_UMTS = 0x4;
    public static final int RAF_IS95A = 0x8;
    public static final int RAF_IS95B = 0x8;
    public static final int RAF_EVDO_0 = 0x10;
    public static final int RAF_EVDO_A = 0x20;
    public static final int RAF_1xRTT = 0x40;
    public static final int RAF_HSDPA = 0x80;
    public static final int RAF_HSUPA = 0x100;
    public static final int RAF_HSPA = 0x200;
    public static final int RAF_EVDO_B = 0x800;
    public static final int RAF_LTE = 0x1000;
    public static final int RAF_EHRPD = 0x2000;
    public static final int RAF_HSPAP = 0x4000;
    public static final int RAF_GSM = 0x8000;
    public static final int RAF_TD_SCDMA = 0x10000;
    public static final int RAF_LTE_CA = 0x40000;
    public static final int RAF_NR = 0x80000;

    private static OplusOSTelephonyManager sInstance = null;

    private Context mContext;

    public OplusOSTelephonyManager(Context context) {
        mContext = context;
    }

    public static OplusOSTelephonyManager getDefault(Context context) {
        if (sInstance == null) {
            sInstance = new OplusOSTelephonyManager(context);
        }
        return sInstance;
    }

    // ---- static helpers ----
    public static boolean getBooleanCarrierConfig(Context context, String name, int subId) { return false; }
    public static int getNetworkTypeFromRaf(int raf) { return 0; }
    public static int oplusgetActiveSubInfoCount(Context context) { return 0; }
    public static int oplusgetDefaultDataPhoneId(Context context) { return 0; }
    public static int oplusgetDefaultDataSubId(Context context) { return 0; }
    public static int oplusgetDefaultSmsPhoneId(Context context) { return 0; }
    public static int oplusgetDefaultSmsSubId(Context context) { return 0; }
    public static int oplusgetDefaultSubId(Context context) { return 0; }
    public static int oplusgetOnDemandDataSubId(Context context) { return 0; }
    public static int oplusgetPhoneId(Context context, int subId) { return 0; }
    public static int oplusgetSlotId(Context context, int subId) { return 0; }
    public static int oplusgetSubId(Context context, int slotId) { return 0; }
    public static int oplusgetSubState(Context context, int subId) { return 0; }
    public static boolean oplusisValidPhoneId(Context context, int phoneId) { return false; }
    public static boolean oplusisValidSlotId(Context context, int slotId) { return false; }
    public static boolean oplusisValidSubId(Context context, int subId) { return false; }
    public static void setDefaultApplication(String packageName, Context context) { }

    // ---- instance API ----
    public void activateSubId(int subId) { }
    public void answerRingingCallGemini(int subId) { }
    public void deactivateSubId(int subId) { }
    public boolean endCallGemini(int subId) { return false; }
    public boolean getBooleanCarrierConfig(String name, int subId) { return false; }
    public int getCallStateGemini(int subId) { return 0; }
    public CellLocation getCellLocation(int subId) { return null; }
    public int getCurrentPhoneTypeGemini(int subId) { return 0; }
    public String getDeviceIdGemini(int subId) { return null; }
    public String getIccCardTypeGemini(int subId) { return null; }
    public String getIccOperatorNumeric(int subId) { return null; }
    public String getLine1NumberGemini(int subId) { return null; }
    public String getNetworkCountryIso(int subId) { return null; }
    public String getNetworkOperatorGemini(int subId) { return null; }
    public int getNetworkTypeGemini(int subId) { return 0; }
    public List getSelectableSubscriptionInfoList() { return Collections.emptyList(); }
    public String getSimOperatorGemini(int subId) { return null; }
    public String getSimSerialNumberGemini(int subId) { return null; }
    public int getSimStateGemini(int subId) { return 0; }
    public int getSubState(int subId) { return 0; }
    public String getSubscriberIdGemini(int subId) { return null; }
    public String getVoiceMailNumberGemini(int subId) { return null; }
    public int getVoiceNetworkTypeGemini(int subId) { return 0; }
    public boolean handlePinMmiForSubscriber(int subId, String dialString) { return false; }
    public boolean hasIccCardGemini(int slotId) { return false; }
    public boolean isDualLteEnabled() { return false; }
    public boolean isDualLteSupportedByPlatform() { return false; }
    public boolean isIdleGemini(int subId) { return false; }
    public boolean isIdleGemini(int subId, String callingPackage) { return false; }
    public boolean isNetworkRoamingGemini(int subId) { return false; }
    public boolean isOffhookGemini(int subId, String callingPackage) { return false; }
    public boolean isOplusHasSoftSimCard() { return false; }
    public boolean isOplusSingleSimCard() { return false; }
    public boolean isRingingGemini(int subId) { return false; }
    public boolean isRingingGemini(int subId, String callingPackage) { return false; }
    public boolean isSimInsert(int slotId) { return false; }
    public boolean isUriFileExist(String uri) { return false; }
    public void listenGemini(Context context, PhoneStateListener listener, int events, int subId) { }
    public void listenGemini(PhoneStateListener listener, int events, int subId) { }
    public int oplusGetActiveSubscriptionsCount(Context context) { return 0; }
    public int oplusGetDataSubscription() { return 0; }
    public int oplusGetDefaultSubscription() { return 0; }
    public String oplusGetIccCardTypeGemini(int subId) { return null; }
    public String oplusGetIccId(int subId) { return null; }
    public boolean oplusGetIccLockEnabled(int subId) { return false; }
    public String oplusGetMeid(int subId) { return null; }
    public String oplusGetOemSpn(int subId) { return null; }
    public String oplusGetPlmnOverride(String numeric, ServiceState serviceState) { return null; }
    public int oplusGetQcomActiveSubscriptionsCount() { return 0; }
    public String oplusGetQcomImeiGemini(int subId) { return null; }
    public String[] oplusGetQcomLTECDMAImei(int subId) { return new String[0]; }
    public String oplusGetScAddressGemini(int subId, int type) { return null; }
    public int oplusGetSimIndicatorState(int subId) { return 0; }
    public int oplusGetSoftSimCardSlotId() { return 0; }
    public boolean oplusIsImsRegistered(Context context, int subId) { return false; }
    public boolean oplusIsQcomSubActive(int subId) { return false; }
    public boolean oplusIsSubActive(int subId) { return false; }
    public boolean oplusIsVolteEnabledByPlatform(Context context, int subId) { return false; }
    public boolean oplusIsVtEnabledByPlatform(Context context, int subId) { return false; }
    public boolean oplusIsWfcEnabledByPlatform(Context context, int subId) { return false; }
    public boolean oplusIsWhiteSIMCard(int subId) { return false; }
    public boolean oplusMvnoMatches(int subId, int mvnoType, String mvnoMatchData, String data) { return false; }
    public void oplusSetDataRoamingEnabled(int subId, boolean enabled) { }
    public void oplusSetDataSubscription(Context context, int subId) { }
    public boolean oplusSetLine1Number(int subId, String number) { return false; }
    public int oplusSetPreferredNetworkType(int subId, int networkType) { return 0; }
    public void oplusSetScAddressGemini(int subId, String address, int type) { }
    public void setDualLteEnabled(boolean enabled) { }
    public boolean showInCallScreenGemini(boolean showDialpad, String a, String b) { return false; }
    public void silenceRingerGemini(int subId) { }
    public void silenceRingerGemini(int subId, String callingPackage) { }
    public boolean supplyPin(String pin, int subId) { return false; }
    public int[] supplyPinReportResult(String pin, int subId) { return new int[0]; }
    public boolean supplyPuk(String puk, String pin, int subId) { return false; }
    public int[] supplyPukReportResult(String puk, String pin, int subId) { return new int[0]; }
}
