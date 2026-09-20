package com.oplus.util;

import android.content.Context;
import android.content.res.Configuration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Stub for com.oplus.util.OplusUnitConversionUtils.
 *
 * Hard-referenced by OppoGallery2. Only the constructor (Context) and
 * getUnitValue(long) are referenced.
 *
 * The OOS constructor loads two OEM string resources (0xc0400cb / 0xc0400cc)
 * that do NOT exist in our build; reproducing that lookup would throw
 * Resources.NotFoundException. We therefore only retain the Context (needed
 * for locale-aware formatting) and skip the OEM resource lookups.
 *
 * getUnitValue(number) == getTransformUnitValue(number, 1024.0): a
 * base-1024 human-readable size string. OEM formatting: B and KB use an
 * integer ("0") pattern, MB and larger use a two-decimal ("0.00") pattern,
 * with a leading-space unit suffix (" B", " KB", ...). This is reproduced
 * with a clean, non-crashing, locale-aware equivalent.
 */
public class OplusUnitConversionUtils {

    private static final double SPECIAL = 1024.0;

    private static final String[] UNITS = {" B", " KB", " MB", " GB", " TB", " PB"};

    private Context mContext;

    public OplusUnitConversionUtils(Context context) {
        mContext = context;
    }

    public String getUnitValue(long number) {
        return getTransformUnitValue(number, SPECIAL);
    }

    public String getTransformUnitValue(long number, double unit) {
        if (unit <= 0) {
            unit = SPECIAL;
        }
        double value = number < 0 ? 0 : (double) number;
        int idx = 0;
        while (value >= unit && idx < UNITS.length - 1) {
            value /= unit;
            idx++;
        }
        String pattern = (idx <= 1) ? "0" : "0.00";
        return formatLocaleNumber(value, pattern) + UNITS[idx];
    }

    private String formatLocaleNumber(double number, String pointNum) {
        Locale locale = Locale.getDefault();
        if (mContext != null) {
            Configuration configuration = mContext.getResources().getConfiguration();
            if (configuration.locale != null) {
                locale = configuration.locale;
            }
        }
        DecimalFormat df = new DecimalFormat(pointNum, new DecimalFormatSymbols(locale));
        return df.format(number);
    }
}
