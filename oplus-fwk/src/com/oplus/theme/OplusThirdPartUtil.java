package com.oplus.theme;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
/**
 * Linkage/closure stub for com.oplus.theme.OplusThirdPartUtil.
 * Referenced externally by OppoGallery2 (only getDefaultTheme()Z). The full
 * public surface of the real OEM class (from OOS oplus-framework.jar) is
 * reproduced so no consumer can hit NoSuchMethodError/NoSuchFieldError.
 *
 * ORACLE: getDefaultTheme() reports true = "the default theme is in use"
 * (a stock, non-themed device). This is both faithful to the real semantics
 * (real impl returns true when bit-0 of the theme flag is unset) and the safe
 * path: the gallery then uses built-in resources instead of attempting
 * third-party theme-zip lookups that do not exist on this port.
 */
public class OplusThirdPartUtil {

    // ---- public static fields (faithful kinds/types from the OEM class) ----
    public static final char[] CHARS = new char[0];
    public static final String LAUNCHER_PACKAGE = "com.android.launcher";
    public static final String O = "";
    public static final String ZIPICONS = "icons";
    public static final String ZIPLAUNCHER = "launcher";
    public static boolean mIsDefaultTheme = false;
    public static String sThemePath = null;

    private OplusThirdPartUtil() {
    }

    // ---- feature/theme queries: report the safe "default theme" answer ----
    public static boolean getDefaultTheme() {
        return true;
    }

    public static boolean getDefaultTheme(int userId) {
        return true;
    }

    public static void setDefaultTheme() {
    }

    public static void setDefaultTheme(int userId) {
    }

    public static void setDefaultTheme(Resources res, int userId) {
    }

    public static boolean clearDir(String dir) {
        return false;
    }

    public static boolean moveFile(String src, String dst, String name) {
        return false;
    }

    // ---- drawable lookups: no themed drawable available -> null (no-op) ----
    public static Drawable getDrawable(int id, Resources res) {
        return null;
    }

    public static Drawable getDrawableForUser(int id, Resources res, int userId) {
        return null;
    }

    public static Drawable getDrawableByName(Resources res, String name) {
        return null;
    }

    public static Drawable getDrawableByName(Resources res, String name, String pkg) {
        return null;
    }

    public static Drawable getDrawableByNameForUser(Resources res, String name, int userId) {
        return null;
    }

    public static Drawable getDrawableByNameForUser(Resources res, String name, String pkg, int userId) {
        return null;
    }

    public static Drawable getDrawableByNameForLauncher(Resources res, String name, int userId) {
        return null;
    }

    public static Drawable getIconDrawableByName(Resources res, String name) {
        return null;
    }

    public static Drawable getIconDrawableByNameForUser(Resources res, String name, int userId) {
        return null;
    }

    public static Drawable getLauncherDrawableByName(Resources res, String name) {
        return null;
    }

    public static Drawable getLauncherDrawableByNameForUser(Resources res, String name, int userId) {
        return null;
    }

    public static String getLauncherName(String pkg) {
        return null;
    }

    public static String getThemeKeyForUser(int userId) {
        return null;
    }

    protected static String getThemePathForUser(Resources res, long themeFlag, int userId) {
        return null;
    }
}
