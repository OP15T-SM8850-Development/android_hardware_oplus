package com.oplus.zoomwindow;

public class OplusZoomWindowManager {

    public static OplusZoomWindowManager sOplusZoomWindowManager = null;

    public static OplusZoomWindowManager getInstance() {
        if (sOplusZoomWindowManager == null) {
            sOplusZoomWindowManager = new OplusZoomWindowManager();
        }
        return sOplusZoomWindowManager;
    }

    public boolean registerZoomWindowObserver(IOplusZoomWindowObserver observer) {
        return false;
    }

    public boolean unregisterZoomWindowObserver(IOplusZoomWindowObserver observer) {
        return false;
    }

    public OplusZoomWindowInfo getCurrentZoomWindowState() {
        return new OplusZoomWindowInfo();
    }

    public boolean isSupportZoomMode(String pkg, int userId, String caller, android.os.Bundle extras) {
        return false;
    }

    public boolean isSupportZoomWindowMode() {
        return false;
    }

    public int startZoomWindow(android.content.Intent intent, android.os.Bundle options, int userId, String caller) {
        return 0;
    }
}
