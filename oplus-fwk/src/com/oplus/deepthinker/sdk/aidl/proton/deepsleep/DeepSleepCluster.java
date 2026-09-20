package com.oplus.deepthinker.sdk.aidl.proton.deepsleep;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Faithful stub of the OEM Parcelable data class DeepSleepCluster.
 *
 * OppoGallery2 constructs instances via all four public constructors below (with real
 * values) and reads back getSleepTimePeriod()/getWakeTimePeriod(), so the constructor
 * field-mapping is ported byte-faithfully from the OOS oplus-framework.jar. Parcelable +
 * Cloneable and the full field set are reproduced so any same-process marshal/round-trip
 * is lossless. Parcel read order in CREATOR matches the OEM writeToParcel order exactly.
 */
public class DeepSleepCluster implements Parcelable, Cloneable {

    public static final int ANOMALY_TYPE = -1;

    private double mSleepTimePeriod;
    private double mWakeTimePeriod;
    private double mMaxDistance;
    private double mSleepMinValue;
    private double mSleepMaxValue;
    private double mWakeMinValue;
    private double mWakeMaxValue;
    private int mClusterId = -1;
    private int mClusterNum = 0;

    public DeepSleepCluster(double sleep, double wake) {
        this.mSleepTimePeriod = sleep;
        this.mWakeTimePeriod = wake;
        this.mMaxDistance = 0.0;
    }

    public DeepSleepCluster(double sleep, double wake, double maxDistance) {
        this.mSleepTimePeriod = sleep;
        this.mWakeTimePeriod = wake;
        this.mMaxDistance = maxDistance;
    }

    public DeepSleepCluster(double sleepTimePeriod, double wakeTimePeriod, double sleepMinValue,
            double sleepMaxValue, double wakeMinValue, double wakeMaxValue) {
        this.mSleepTimePeriod = sleepTimePeriod;
        this.mWakeTimePeriod = wakeTimePeriod;
        this.mMaxDistance = 0.0;
        this.mSleepMinValue = sleepMinValue;
        this.mSleepMaxValue = sleepMaxValue;
        this.mWakeMinValue = wakeMinValue;
        this.mWakeMaxValue = wakeMaxValue;
    }

    public DeepSleepCluster(int clusterId, double sleep, double wake, double maxDistance) {
        this.mSleepTimePeriod = sleep;
        this.mWakeTimePeriod = wake;
        this.mMaxDistance = maxDistance;
        this.mClusterId = clusterId;
    }

    private DeepSleepCluster(Parcel in) {
        this.mSleepTimePeriod = in.readDouble();
        this.mWakeTimePeriod = in.readDouble();
        this.mMaxDistance = in.readDouble();
        this.mClusterId = in.readInt();
        this.mClusterNum = in.readInt();
        this.mSleepMinValue = in.readDouble();
        this.mSleepMaxValue = in.readDouble();
        this.mWakeMinValue = in.readDouble();
        this.mWakeMaxValue = in.readDouble();
    }

    public double getSleepTimePeriod() { return mSleepTimePeriod; }
    public double getWakeTimePeriod() { return mWakeTimePeriod; }
    public double getMaxDistance() { return mMaxDistance; }
    public double getSleepMinValue() { return mSleepMinValue; }
    public double getSleepMaxValue() { return mSleepMaxValue; }
    public double getWakeMinValue() { return mWakeMinValue; }
    public double getWakeMaxValue() { return mWakeMaxValue; }
    public int getClusterId() { return mClusterId; }
    public int getClusterNum() { return mClusterNum; }

    public void setSleepTimePeriod(double v) { this.mSleepTimePeriod = v; }
    public void setWakeTimePeriod(double v) { this.mWakeTimePeriod = v; }
    public void setMaxDistance(double v) { this.mMaxDistance = v; }
    public void setClusterId(int v) { this.mClusterId = v; }
    public void setClusterNum(int v) { this.mClusterNum = v; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(mSleepTimePeriod);
        dest.writeDouble(mWakeTimePeriod);
        dest.writeDouble(mMaxDistance);
        dest.writeInt(mClusterId);
        dest.writeInt(mClusterNum);
        dest.writeDouble(mSleepMinValue);
        dest.writeDouble(mSleepMaxValue);
        dest.writeDouble(mWakeMinValue);
        dest.writeDouble(mWakeMaxValue);
    }

    @Override
    public DeepSleepCluster clone() {
        try {
            return (DeepSleepCluster) super.clone();
        } catch (CloneNotSupportedException e) {
            DeepSleepCluster copy = new DeepSleepCluster(mClusterId, mSleepTimePeriod,
                    mWakeTimePeriod, mMaxDistance);
            copy.mClusterNum = this.mClusterNum;
            copy.mSleepMinValue = this.mSleepMinValue;
            copy.mSleepMaxValue = this.mSleepMaxValue;
            copy.mWakeMinValue = this.mWakeMinValue;
            copy.mWakeMaxValue = this.mWakeMaxValue;
            return copy;
        }
    }

    @Override
    public String toString() {
        return "DeepSleepCluster{mSleepTimePeriod=" + mSleepTimePeriod
                + ", mWakeTimePeriod=" + mWakeTimePeriod
                + ", mMaxDistance=" + mMaxDistance
                + ", mClusterId=" + mClusterId
                + ", mClusterNum=" + mClusterNum + "}";
    }

    public static final Parcelable.Creator<DeepSleepCluster> CREATOR =
            new Parcelable.Creator<DeepSleepCluster>() {
                @Override
                public DeepSleepCluster createFromParcel(Parcel in) {
                    return new DeepSleepCluster(in);
                }

                @Override
                public DeepSleepCluster[] newArray(int size) {
                    return new DeepSleepCluster[size];
                }
            };
}
