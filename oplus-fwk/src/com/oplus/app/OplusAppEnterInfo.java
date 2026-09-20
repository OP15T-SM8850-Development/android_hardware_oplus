package com.oplus.app;

public class OplusAppEnterInfo {

    public OplusAppEnterInfo() {}

    public android.os.Bundle extension;
    public boolean firstStart;
    public android.content.Intent intent;
    public String launchedFromPackage;
    public boolean multiApp;
    public String targetName;
    public int windowMode;

    @Override
    public String toString() {
        return "OplusAppEnterInfo{targetName=" + targetName
                + ", launchedFromPackage=" + launchedFromPackage
                + ", windowMode=" + windowMode
                + ", firstStart=" + firstStart
                + ", multiApp=" + multiApp + "}";
    }
}
