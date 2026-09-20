package android.view;

public class OplusWindowManager {

    public OplusWindowManager() {}

    public void requestKeyguard(String command) {}
    public boolean setPreferredDisplayMode(int mode) { return false; }

    public void getFocusedWindowFrame(android.graphics.Rect outRect) {
    }

    public int getLongshotSurfaceLayerByType(int type) {
        return -1;
    }

    public com.oplus.app.OplusScreenShotResult getScreenshot(com.oplus.app.OplusScreenShotOptions options) {
        return null;
    }

    public java.util.List<android.graphics.Rect> getSplitAreaRegion() {
        return new java.util.ArrayList<>();
    }

    public boolean isInputShow() {
        return false;
    }

    public void registerOplusWindowStateObserver(android.view.IOplusWindowStateObserver observer) {
    }

    public void unregisterOplusWindowStateObserver(android.view.IOplusWindowStateObserver observer) {
    }
}
