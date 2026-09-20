package com.oplus.wrapper.content.pm;

public class ResolveInfo {
    private final android.content.pm.ResolveInfo mResolveInfo;

    public ResolveInfo(android.content.pm.ResolveInfo resolveInfo) {
        this.mResolveInfo = resolveInfo;
    }

    public int getTargetUserId() {
        return 0;
    }
}
