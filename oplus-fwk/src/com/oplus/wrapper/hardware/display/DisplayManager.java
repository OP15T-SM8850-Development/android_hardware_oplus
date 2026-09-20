package com.oplus.wrapper.hardware.display;

public class DisplayManager {
    private final android.hardware.display.DisplayManager mDisplayManager;

    public DisplayManager(android.hardware.display.DisplayManager displayManager) {
        this.mDisplayManager = displayManager;
    }

    public WifiDisplayStatus getWifiDisplayStatus() {
        return new WifiDisplayStatus();
    }
}
