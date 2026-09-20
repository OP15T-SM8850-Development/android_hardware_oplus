package android.app;

/* loaded from: classes.dex */
public class OplusNotificationManager {
    private static final String TAG = "OplusNotificationManager";

    public OplusNotificationManager() {
    }

    public String[] getEnableNavigationApps(int userId) {
        return new String[0];
    }

    public String getStdid(String pkg, int userId, String type) {
        return null;
    }

    public boolean isDriveNavigationMode(String pkg, int userId) {
        return false;
    }

    public boolean isSuppressedByDriveMode(int userId) {
        return false;
    }

    public void setSuppressedByDriveMode(boolean suppressed, int userId) {
    }
}
