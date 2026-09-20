package android.content;

import android.os.Parcel;

/**
 * Class-closure stub for the OEM boot-framework class android.content.OplusBaseIntent.
 *
 * In OxygenOS this is the injected superclass of android.content.Intent. The shipped
 * OEM apk OppoGallery2 (com.oplus.aiunit.vision.d2d) hard-references it via:
 *     const-class  Landroid/content/OplusBaseIntent;
 *     check-cast   Landroid/content/OplusBaseIntent;
 *     invoke-virtual ...->setOplusFlags(I)V
 * The const-class / check-cast are class-load references: if the class is absent they
 * raise NoClassDefFoundError (a java.lang.Error, NOT caught by catch(Exception)).
 * Providing this class on the boot classpath (oplus-fwk.jar) makes those references link.
 *
 * Our AOSP android.content.Intent does not actually extend this type; the caller reaches
 * setOplusFlags only through a safe instanceof-cast helper, so a null result simply skips
 * the call. All members are safe no-ops / default returns.
 *
 * Public constant + method surface ported faithfully from the OOS oplus-modified
 * framework.jar (android/content/OplusBaseIntent) to close future linkage refs in one pass.
 */
public class OplusBaseIntent {
    public static final int FLAG_RECEIVER_OPLUSQUEUE = 0x80000;
    public static final int FLAG_RECEIVER_QUEUE_PRIOR = 0x100000;
    public static final int OPLUS_FLAG_ACTIVITY_CONTINUE_PRIVACY = 0x20000000;
    public static final int OPLUS_FLAG_ACTIVITY_CONTINUE_REQUIRED = 0x10000000;
    public static final int OPLUS_FLAG_ACTIVITY_KEEP_RESUM_WHEN_SLEEPING = 0x40000000;
    public static final int OPLUS_FLAG_ACTIVITY_SECURE_POLICY = 0x80000000; // -0x80000000 (Integer.MIN_VALUE)
    public static final int OPLUS_FLAG_BELONG_TASKVIEW = 0x40000;
    public static final int OPLUS_FLAG_KEEP_CHOOSER_LABEL_FOR_MULTI_APP = 0x2000;
    public static final int OPLUS_FLAG_MULTI_APP_DIRECT_MULTI_APP = 0x1000;
    public static final int OPLUS_FLAG_MULTI_APP_SKIP_CHOOSER = 0x800;
    public static final int OPLUS_FLAG_MUTIL_APP = 0x400;
    public static final int OPLUS_FLAG_MUTIL_CHOOSER = 0x200;
    public static final int OPLUS_FLAG_RECEIVER_OPLUSQUEUE = 0x2;
    public static final int OPLUS_FLAG_RECEIVER_QUEUE_PRIOR = 0x1;

    public OplusBaseIntent() {
    }

    // Referenced by OppoGallery2 (com.oplus.aiunit.vision.d2d).
    public void setOplusFlags(int oplusFlags) {
    }

    public void addOplusFlags(int oplusFlags) {
    }

    public void removeOplusFlags(int oplusFlags) {
    }

    public int getOplusFlags() {
        return 0;
    }

    public int fillIn(OplusBaseIntent other, int flags) {
        return 0;
    }

    public int getCallingUid() {
        return 0;
    }

    public void setCallingUid(int uid) {
    }

    public int getUid() {
        return 0;
    }

    public void setUid(int uid) {
    }

    public int getPid() {
        return 0;
    }

    public void setPid(int pid) {
    }

    public int getOplusUserId() {
        return 0;
    }

    public void setOplusUserId(int userId) {
    }

    public int getIsForFreeForm() {
        return 0;
    }

    public void setIsForFreeForm(int value) {
    }

    public int getIsFromGameSpace() {
        return 0;
    }

    public void setIsFromGameSpace(int value) {
    }

    public int getPairLaunchWindowingMode() {
        return 0;
    }

    public void setPairLaunchWindowingMode(int mode) {
    }

    public int getStartFromOcar() {
        return 0;
    }

    public void setStartFromOcar(int value) {
    }

    public void readFromParcel(Parcel in) {
    }

    public void writeToParcel(Parcel out, int flags) {
    }

    @Override
    public String toString() {
        return "OplusBaseIntent";
    }
}
