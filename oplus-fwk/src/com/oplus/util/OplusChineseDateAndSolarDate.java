package com.oplus.util;

import java.time.LocalDate;

/**
 * Stub for com.oplus.util.OplusChineseDateAndSolarDate.
 *
 * Hard-referenced by OppoGallery2 (via the com.heytap.addon.util wrapper,
 * which delegates here on Android R+). The five referenced public static
 * methods match the OOS descriptors exactly:
 *   ChineseDateToSunDate(III)[I
 *   SunDateToChineseDate(III)[I
 *   GetChLeapMonth(I)I
 *   GetChMonthDays(II)I
 *   GetSolarMonthDays(II)I
 *
 * CRITICAL: the OOS class runs a <clinit> that calls
 *   System.loadLibrary("ChineseDateAndSolarDate")
 * and delegates every method to native code. That native library
 * (libChineseDateAndSolarDate.so) does NOT exist in our build, so a faithful
 * port would throw UnsatisfiedLinkError at class-init time -- a
 * java.lang.LinkageError (Error, NOT Exception) that would NOT be caught by
 * the app's catch(Exception) and would hard-crash. We therefore implement the
 * conversions in pure Java using the canonical 1900-2100 lunar table, with no
 * native library and no static initializer that can fail. All methods are
 * range-guarded so out-of-range input returns a safe default instead of
 * throwing.
 */
public class OplusChineseDateAndSolarDate {

    // Canonical Chinese lunar calendar table for solar years 1900..2100.
    private static final int[] LUNAR_INFO = {
        0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, // 1900-1909
        0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977, // 1910-1919
        0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, // 1920-1929
        0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950, // 1930-1939
        0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, // 1940-1949
        0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5b0, 0x14573, 0x052b0, 0x0a9a8, 0x0e950, 0x06aa0, // 1950-1959
        0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, // 1960-1969
        0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6, // 1970-1979
        0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, // 1980-1989
        0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, // 1990-1999
        0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, // 2000-2009
        0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, // 2010-2019
        0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530, // 2020-2029
        0x05aa0, 0x076a3, 0x096d0, 0x04afb, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, // 2030-2039
        0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0, // 2040-2049
        0x14b63, 0x09370, 0x049f8, 0x04970, 0x064b0, 0x168a6, 0x0ea50, 0x06b20, 0x1a6c4, 0x0aae0, // 2050-2059
        0x0a2e0, 0x0d2e3, 0x0c960, 0x0d557, 0x0d4a0, 0x0da50, 0x05d55, 0x056a0, 0x0a6d0, 0x055d4, // 2060-2069
        0x052d0, 0x0a9b8, 0x0a950, 0x0b4a0, 0x0b6a6, 0x0ad50, 0x055a0, 0x0aba4, 0x0a5b0, 0x052b0, // 2070-2079
        0x0b273, 0x06930, 0x07337, 0x06aa0, 0x0ad50, 0x14b55, 0x04b60, 0x0a570, 0x054e4, 0x0d160, // 2080-2089
        0x0e968, 0x0d520, 0x0daa0, 0x16aa6, 0x056d0, 0x04ae0, 0x0a9d4, 0x0a2d0, 0x0d150, 0x0f252, // 2090-2099
        0x0d520  // 2100
    };

    private static final int BASE_YEAR = 1900;
    private static final int MAX_YEAR = 2100;
    // Solar date corresponding to lunar 1900-01-01.
    private static final LocalDate LUNAR_EPOCH = LocalDate.of(1900, 1, 31);

    public OplusChineseDateAndSolarDate() {
    }

    private static boolean inRange(int year) {
        return year >= BASE_YEAR && year <= MAX_YEAR;
    }

    // Number of days in the given lunar year.
    private static int lunarYearDays(int year) {
        int sum = 348;
        for (int i = 0x8000; i > 0x8; i >>= 1) {
            sum += ((LUNAR_INFO[year - BASE_YEAR] & i) != 0) ? 1 : 0;
        }
        return sum + lunarLeapDays(year);
    }

    // Leap month (1..12) for the given lunar year, or 0 if none.
    private static int lunarLeapMonth(int year) {
        return LUNAR_INFO[year - BASE_YEAR] & 0xf;
    }

    // Number of days in the leap month of the given lunar year (0 if no leap).
    private static int lunarLeapDays(int year) {
        if (lunarLeapMonth(year) != 0) {
            return ((LUNAR_INFO[year - BASE_YEAR] & 0x10000) != 0) ? 30 : 29;
        }
        return 0;
    }

    // Number of days in lunar month m (1..12) of the given lunar year.
    private static int lunarMonthDays(int year, int month) {
        if (month < 1 || month > 12) {
            return 30;
        }
        return ((LUNAR_INFO[year - BASE_YEAR] & (0x10000 >> month)) != 0) ? 30 : 29;
    }

    /**
     * Days in a Gregorian (solar) month. (II)I
     */
    public static int GetSolarMonthDays(int iSolarYear, int iSolarMonth) {
        if (iSolarMonth < 1 || iSolarMonth > 12 || iSolarYear < 1) {
            return 30;
        }
        return LocalDate.of(iSolarYear, iSolarMonth, 1).lengthOfMonth();
    }

    /**
     * Days in a lunar month. (II)I
     */
    public static int GetChMonthDays(int iChineseYear, int iChineseMonth) {
        if (!inRange(iChineseYear)) {
            return 30;
        }
        return lunarMonthDays(iChineseYear, iChineseMonth);
    }

    /**
     * Leap lunar month for the year (0 if none). (I)I
     */
    public static int GetChLeapMonth(int iChineseYear) {
        if (!inRange(iChineseYear)) {
            return 0;
        }
        return lunarLeapMonth(iChineseYear);
    }

    /**
     * Convert a Gregorian date to a lunar date. (III)[I
     * Returns {lunarYear, lunarMonth, lunarDay}.
     */
    public static int[] SunDateToChineseDate(int iSunDateYear, int iSunDateMonth, int iSunDateDay) {
        if (iSunDateMonth < 1 || iSunDateMonth > 12 || iSunDateDay < 1 || iSunDateDay > 31) {
            return new int[] {iSunDateYear, iSunDateMonth, iSunDateDay};
        }
        LocalDate solar;
        try {
            solar = LocalDate.of(iSunDateYear, iSunDateMonth, iSunDateDay);
        } catch (RuntimeException e) {
            return new int[] {iSunDateYear, iSunDateMonth, iSunDateDay};
        }

        long offset = solar.toEpochDay() - LUNAR_EPOCH.toEpochDay();
        if (offset < 0 || iSunDateYear < BASE_YEAR || iSunDateYear > MAX_YEAR) {
            // Outside the supported table range: echo input rather than crash.
            return new int[] {iSunDateYear, iSunDateMonth, iSunDateDay};
        }

        int year = BASE_YEAR;
        int daysInYear = 0;
        for (year = BASE_YEAR; year <= MAX_YEAR && offset > 0; year++) {
            daysInYear = lunarYearDays(year);
            offset -= daysInYear;
        }
        if (offset < 0) {
            offset += daysInYear;
            year--;
        }
        int lunarYear = year;

        int leap = lunarLeapMonth(lunarYear);
        boolean isLeap = false;
        int month = 1;
        int daysInMonth = 0;
        for (month = 1; month <= 12 && offset > 0; month++) {
            if (leap > 0 && month == (leap + 1) && !isLeap) {
                --month;
                isLeap = true;
                daysInMonth = lunarLeapDays(lunarYear);
            } else {
                daysInMonth = lunarMonthDays(lunarYear, month);
            }
            if (isLeap && month == (leap + 1)) {
                isLeap = false;
            }
            offset -= daysInMonth;
        }
        if (offset == 0 && leap > 0 && month == leap + 1) {
            if (isLeap) {
                isLeap = false;
            } else {
                isLeap = true;
                --month;
            }
        }
        if (offset < 0) {
            offset += daysInMonth;
            --month;
        }
        int lunarMonth = month;
        int lunarDay = (int) offset + 1;
        return new int[] {lunarYear, lunarMonth, lunarDay};
    }

    /**
     * Convert a lunar date to a Gregorian date. (III)[I
     * Returns {solarYear, solarMonth, solarDay}. The lunar month is treated as
     * an ordinal (no leap-month flag is provided by this signature).
     */
    public static int[] ChineseDateToSunDate(int iChineseYear, int iChineseMonth, int iChineseDay) {
        if (!inRange(iChineseYear) || iChineseMonth < 1 || iChineseMonth > 12) {
            return new int[] {iChineseYear, iChineseMonth, iChineseDay};
        }

        long offset = 0;
        for (int y = BASE_YEAR; y < iChineseYear; y++) {
            offset += lunarYearDays(y);
        }
        int leap = lunarLeapMonth(iChineseYear);
        for (int m = 1; m < iChineseMonth; m++) {
            offset += lunarMonthDays(iChineseYear, m);
            if (leap > 0 && m == leap) {
                offset += lunarLeapDays(iChineseYear);
            }
        }
        offset += (iChineseDay < 1 ? 0 : iChineseDay - 1);

        LocalDate solar = LUNAR_EPOCH.plusDays(offset);
        return new int[] {solar.getYear(), solar.getMonthValue(), solar.getDayOfMonth()};
    }
}
