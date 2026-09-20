package com.oplus.app;

public class OplusAppExitInfo {

    public OplusAppExitInfo() {}

    public android.os.Bundle extension;
    public boolean hasResumingActivity;
    public boolean isResumingFirstStart;
    public boolean isResumingMultiApp;
    public String resumingActivityName;
    public String resumingPackageName;
    public int resumingWindowMode;
    public String targetName;

    @Override
    public String toString() {
        return "OplusAppExitInfo{targetName=" + targetName
                + ", resumingPackageName=" + resumingPackageName
                + ", resumingActivityName=" + resumingActivityName
                + ", resumingWindowMode=" + resumingWindowMode
                + ", hasResumingActivity=" + hasResumingActivity
                + ", isResumingFirstStart=" + isResumingFirstStart
                + ", isResumingMultiApp=" + isResumingMultiApp + "}";
    }
}
