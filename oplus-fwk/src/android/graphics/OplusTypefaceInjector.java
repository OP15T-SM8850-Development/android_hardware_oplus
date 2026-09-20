package android.graphics;

import java.util.Collections;
import java.util.Map;

/**
 * Closure stub for android.graphics.OplusTypefaceInjector.
 *
 * Provided surface for OEM apks (OppoGallery2 hard-reads OPLUSUI_MEDIUM) so the
 * class + field resolve at load time (no NoClassDefFoundError / NoSuchFieldError).
 * Mirrors the real OOS oplus-framework.jar class kind (public final) and full
 * static surface; bodies are safe non-crashing defaults.
 *
 * Referenced member set (verified across gallery/camera/appplatform dex):
 *   OPLUSUI_MEDIUM : Landroid/graphics/Typeface;
 */
public final class OplusTypefaceInjector {
    // --- Faithful string constants (harmless literals). ---
    public static final String DEFAULT_FONT_CONFIG_FILE = "/system/etc/fonts.xml";
    public static final String FBE_FONT_CONFIG_FILE = "/system_ext/etc/fonts_base.xml";
    public static final String SECOND_FONT_CONFIG_FILE = "/system_ext/etc/fonts_base.xml";
    public static final String OPLUS_CUSTOMIZATION_FONTS_PATH = "/system_ext/fonts/";
    public static final String OPLUS_CUSTOMIZATION_FONTS_XML =
            "/system_ext/etc/fonts_customization.xml";

    public static final boolean sIsFBESupport = false;

    // --- Referenced by OppoGallery2. Safe non-crashing default = system default
    //     typeface (renderable, never null) instead of OOS null-until-fontinit. ---
    public static Typeface OPLUSUI_MEDIUM = Typeface.DEFAULT;
    public static Typeface OPLUSUI_VF = Typeface.DEFAULT;

    public OplusTypefaceInjector() {
    }

    public static boolean isSystemTypeface(Typeface typeface) {
        return false;
    }

    public static boolean isSystemTypeface(String fontFamily) {
        return false;
    }

    public static Typeface[] getSystemDefaultTypefaces() {
        return new Typeface[0];
    }

    public static Map<String, Typeface> getSystemFontMap() {
        return Collections.emptyMap();
    }

    public static void dumpSysTypeface() {
        // no-op
    }
}
