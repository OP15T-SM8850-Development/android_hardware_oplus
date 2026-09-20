package android.os.olc;

import android.os.Bundle;

/**
 * Closure stub for OEM android.os.olc.OlcManager.
 * OLC (OnLine Configuration / diagnostics) event bridge to the "olc"
 * system service. Hard-referenced by OppoGallery2, which calls the static
 * sendEvent(Bundle) for fire-and-forget telemetry.
 *
 * On our build there is no "olc" ServiceManager service, so we mirror the
 * OEM class's own ERROR_SERVICE_NOT_INIT path: return -1 without touching
 * any binder. Non-crashing, and truthful (nothing is delivered). The real
 * companion types (IOlcService, ExceptionInfo) are NOT referenced by any
 * shipped consumer and are intentionally not part of this stub's surface.
 */
public class OlcManager {

    /** Matches the OEM public constant; harmless, faithful. */
    public static final String OLC_SERVICE_NAME = "olc";

    private static final int ERROR_SERVICE_NOT_INIT = -1;

    public OlcManager() {
    }

    /**
     * Consumer-referenced: Landroid/os/olc/OlcManager;->sendEvent(Landroid/os/Bundle;)I
     * No olc service present -> report not-initialized (-1), no-op otherwise.
     */
    public static int sendEvent(Bundle params) {
        return ERROR_SERVICE_NOT_INIT;
    }
}
