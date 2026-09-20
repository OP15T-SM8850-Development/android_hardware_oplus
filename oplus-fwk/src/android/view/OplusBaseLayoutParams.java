package android.view;

/**
 * Stub for OEM android.view.OplusBaseLayoutParams (lives in the AOSP-modified
 * framework.jar on OOS, where WindowManager.LayoutParams extends it).
 * OppoGallery2 check-casts LayoutParams instances to this type and reads/writes
 * the oplusFlags / ignoreHomeMenuKey int fields. We provide the class + fields so
 * class-load / field linkage cannot NoClassDefFoundError / NoSuchFieldError.
 * (Runtime check-cast of a plain AOSP LayoutParams yields a catchable
 * ClassCastException, not a linkage Error, and the gallery guards most sites
 * with instanceof; gallery is not on the AppPlatform boot path.)
 * Superclass matches OOS: extends ViewGroup.LayoutParams.
 */
public class OplusBaseLayoutParams extends ViewGroup.LayoutParams {

    public int ignoreHomeMenuKey;
    public int oplusFlags;

    public OplusBaseLayoutParams() {
        super(WRAP_CONTENT, WRAP_CONTENT);
    }

    public OplusBaseLayoutParams(int width, int height) {
        super(width, height);
    }
}
