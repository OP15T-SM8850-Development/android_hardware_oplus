package com.oplus.deepthinker.sdk.aidl.proton.deepsleep;

/**
 * Minimal stub of the OEM Parcelable class TotalPredictResult.
 *
 * OppoGallery2 references exactly getSleepCluster()/getWakeCluster(), both returning a
 * DeepSleepCluster. The real class is Parcelable and additionally carries two TrainConfig
 * fields (getOptimalSleepConfig/getOptimalWakeConfig); those members are NOT referenced by
 * any shipped consumer, so they are omitted to avoid pulling an otherwise-unreferenced
 * TrainConfig companion into the closure. A plain class loads cleanly and satisfies every
 * referenced member with a safe default (null clusters).
 */
public class TotalPredictResult {

    private DeepSleepCluster mSleepCluster;
    private DeepSleepCluster mWakeCluster;

    public TotalPredictResult() {
    }

    public TotalPredictResult(DeepSleepCluster sleepCluster, DeepSleepCluster wakeCluster) {
        this.mSleepCluster = sleepCluster;
        this.mWakeCluster = wakeCluster;
    }

    public DeepSleepCluster getSleepCluster() {
        return mSleepCluster;
    }

    public DeepSleepCluster getWakeCluster() {
        return mWakeCluster;
    }

    public void setSleepCluster(DeepSleepCluster c) {
        this.mSleepCluster = c;
    }

    public void setWakeCluster(DeepSleepCluster c) {
        this.mWakeCluster = c;
    }

    @Override
    public String toString() {
        return "TotalPredictResult{mSleepCluster=" + mSleepCluster
                + ", mWakeCluster=" + mWakeCluster + "}";
    }
}
