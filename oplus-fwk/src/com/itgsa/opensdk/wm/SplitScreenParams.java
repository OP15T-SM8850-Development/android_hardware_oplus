package com.itgsa.opensdk.wm;

import android.content.Intent;

/**
 * Stub for OEM com.itgsa.opensdk.wm.SplitScreenParams + nested Builder
 * (compiles to com.itgsa.opensdk.wm.SplitScreenParams$Builder). Faithful to OOS:
 * final immutable value object built via the fluent Builder.
 */
public final class SplitScreenParams {
    private final Intent mLaunchIntent;
    private final int mPosition;
    private final boolean mSelfSplit;

    private SplitScreenParams(boolean selfSplit, Intent launchIntent, int position) {
        this.mSelfSplit = selfSplit;
        this.mLaunchIntent = launchIntent;
        this.mPosition = position;
    }

    public Intent getLaunchIntent() {
        return mLaunchIntent;
    }

    public int getLaunchPosition() {
        return mPosition;
    }

    public boolean isSelfSplit() {
        return mSelfSplit;
    }

    public static class Builder {
        private Intent mLaunchIntent;
        private int mPosition;
        private boolean mSelfSplit;

        public Builder() {
        }

        public Builder(SplitScreenParams params) {
            if (params != null) {
                this.mLaunchIntent = params.mLaunchIntent;
                this.mPosition = params.mPosition;
                this.mSelfSplit = params.mSelfSplit;
            }
        }

        public Builder setLaunchIntent(Intent launchIntent) {
            this.mLaunchIntent = launchIntent;
            return this;
        }

        public Builder setLaunchPosition(int position) {
            this.mPosition = position;
            return this;
        }

        public Builder setSelfSplit() {
            this.mSelfSplit = true;
            return this;
        }

        public SplitScreenParams build() {
            return new SplitScreenParams(mSelfSplit, mLaunchIntent, mPosition);
        }
    }
}
