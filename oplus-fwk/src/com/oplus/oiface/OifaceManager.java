package com.oplus.oiface;

/**
 * Closure stub for the OEM boot-classpath manager com.oplus.oiface.OifaceManager.
 *
 * Consumer: OppoGallery2 hard-references exactly three members:
 *   - getInstance(String)            (invoke-static, result dereferenced)
 *   - getSupportGameStartPackage()   (invoke-virtual)
 *   - registerOifaceCallback(IOIfaceCallback)  (invoke-virtual)
 *
 * getInstance replicates the real double-checked-locking singleton (keyed on the
 * class, seeded with the first identity) so it never returns null and the two
 * instance methods always resolve on a live object.
 *
 * OIface is the game-performance HAL glue; none of these gate any olive / livephoto
 * / DV / camera feature, so the two instance methods return safe, non-crashing
 * no-op defaults:
 *   - getSupportGameStartPackage(): empty string (never null -> no NPE / no game
 *     package ever matches -> gallery skips its game path).
 *   - registerOifaceCallback(...): false (registration cleanly reported as failed;
 *     the callback simply never fires, which is correct for a device with no OIface
 *     service backing this stub).
 *
 * The real class also holds an IOIfaceInternalService binder + a large game-control
 * API surface; none of that is referenced by our shipped consumers, so it is
 * intentionally omitted to avoid pulling in unreferenced companion types.
 */
public class OifaceManager {

    private static volatile OifaceManager sInstance;

    private final String mIdentity;

    private OifaceManager(String identity) {
        this.mIdentity = identity;
    }

    public static OifaceManager getInstance(String identity) {
        if (sInstance == null) {
            synchronized (OifaceManager.class) {
                if (sInstance == null) {
                    sInstance = new OifaceManager(identity);
                }
            }
        }
        return sInstance;
    }

    public String getSupportGameStartPackage() {
        return "";
    }

    public boolean registerOifaceCallback(IOIfaceCallback callback) {
        return false;
    }
}
