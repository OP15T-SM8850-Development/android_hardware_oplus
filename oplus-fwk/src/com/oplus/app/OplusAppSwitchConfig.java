package com.oplus.app;

import java.util.List;

public class OplusAppSwitchConfig {

    public void addAppConfig(int type, List<String> list) {}

    public java.util.HashSet<String> mActivitySet = new java.util.HashSet<>();
    public int observerFingerPrint;

    public List<String> getConfigs(int type) {
        return new java.util.ArrayList<>();
    }

    public void removeAppConfig(int type) {
    }

    @Override
    public String toString() {
        return "OplusAppSwitchConfig{observerFingerPrint=" + observerFingerPrint
                + ", mActivitySet=" + mActivitySet + "}";
    }
}
