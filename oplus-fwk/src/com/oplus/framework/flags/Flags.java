package com.oplus.framework.flags;

/**
 * Stub for the aconfig-generated feature-flag accessor
 * com.oplus.framework.flags.Flags.
 *
 * OppoGallery2 (com.oplus.aiunit.vision.*) hard-references the following via
 * invoke-static: they gate confidential AI-vision code branches. None are
 * olive/livephoto/DV features, and returning false cleanly follows the default
 * branch at every call site, so we return the safe default (false).
 *
 * Real aconfig Flags classes are declared `public final` with `public static
 * boolean` accessors; matched here so invoke-static resolution is exact.
 */
public final class Flags {

    private Flags() {
    }

    public static boolean coloros1502ConfidentialZhuque() {
        return false;
    }

    public static boolean coloros1600ConfidentialInfinite() {
        return false;
    }

    public static boolean coloros1600ConfidentialOdc() {
        return false;
    }
}
