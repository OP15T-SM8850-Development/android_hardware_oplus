package com.oplus.wrapper.view;

public class WindowManagerGlobal {
    private static WindowManagerGlobal sInstance;

    private WindowManagerGlobal() {
    }

    public static WindowManagerGlobal getInstance() {
        if (sInstance == null) {
            sInstance = new WindowManagerGlobal();
        }
        return sInstance;
    }

    public void trimMemory(int level) {
    }
}
