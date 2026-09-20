package android.view;

/**
 * Stub for OEM android.view.OplusScreenDragUtil. OppoGallery2 invokes these
 * static helpers (invoke-static). All calls resolve to safe no-op defaults:
 * no drag state, zero geometry/offset, identity scale (1.0f) and identity
 * offset-position mapping so no divide-by-zero / degenerate transform occurs.
 */
public class OplusScreenDragUtil {

    public OplusScreenDragUtil() {}

    public static int getHeight() {
        return 0;
    }

    public static int getWidth() {
        return 0;
    }

    public static int getOffsetX() {
        return 0;
    }

    public static int getOffsetY() {
        return 0;
    }

    public static float getScale() {
        return 1.0f;
    }

    public static float getOffsetPosX(float x) {
        return x;
    }

    public static float getOffsetPosY(float y) {
        return y;
    }

    public static boolean isDragState() {
        return false;
    }
}
