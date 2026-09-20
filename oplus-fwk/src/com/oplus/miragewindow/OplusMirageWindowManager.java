package com.oplus.miragewindow;

import java.util.ArrayList;
import java.util.List;

/* Stub of OEM com.oplus.miragewindow.OplusMirageWindowManager (singleton). */
public class OplusMirageWindowManager {
    private static volatile OplusMirageWindowManager sInstance;

    private OplusMirageWindowManager() {
    }

    public static OplusMirageWindowManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusMirageWindowManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusMirageWindowManager();
                }
            }
        }
        return sInstance;
    }

    public List<OplusCastScreenState> getCastScreenStateList() {
        return new ArrayList<OplusCastScreenState>();
    }
}
