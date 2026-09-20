package com.oplus.app;

import android.graphics.Rect;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.SurfaceControl;

/* loaded from: classes.dex */
public class OplusScreenShotOptions implements Parcelable {
    public static final Parcelable.Creator<OplusScreenShotOptions> CREATOR = new Parcelable.Creator<OplusScreenShotOptions>() { // from class: com.oplus.app.OplusScreenShotOptions.1
        @Override // android.os.Parcelable.Creator
        public OplusScreenShotOptions createFromParcel(Parcel source) {
            return new OplusScreenShotOptions(source);
        }

        @Override // android.os.Parcelable.Creator
        public OplusScreenShotOptions[] newArray(int size) {
            return new OplusScreenShotOptions[size];
        }
    };
    public int mDisplayId;
    public SurfaceControl[] mExcludeLayers;
    public IBinder[] mExcludeWindows;
    public boolean mFullDisplay;
    public SurfaceControl mLayer;
    public float mScaleIndex = -1.0f;
    public Rect mSourceCrop;
    public int[] mTasks;

    public OplusScreenShotOptions() {
    }

    private OplusScreenShotOptions(Parcel source) {
        int taskSize = source.readInt();
        if (taskSize > 0) {
            this.mTasks = new int[taskSize];
            source.readIntArray(this.mTasks);
        }
        int layerFlag = source.readInt();
        if (layerFlag > 0) {
            this.mLayer = source.readTypedObject(SurfaceControl.CREATOR);
        }
        int excludeLayersSize = source.readInt();
        if (excludeLayersSize > 0) {
            this.mExcludeLayers = new SurfaceControl[excludeLayersSize];
            source.readTypedArray(this.mExcludeLayers, SurfaceControl.CREATOR);
        }
        this.mExcludeWindows = source.createBinderArray();
        this.mSourceCrop = source.readParcelable(Rect.class.getClassLoader());
        this.mFullDisplay = source.readBoolean();
        this.mDisplayId = source.readInt();
        this.mScaleIndex = source.readFloat();
    }

    public OplusScreenShotOptions(OplusScreenShotOptions other) {
        if (other != null) {
            this.mTasks = other.mTasks;
            this.mLayer = other.mLayer;
            this.mExcludeLayers = other.mExcludeLayers;
            this.mExcludeWindows = other.mExcludeWindows;
            this.mSourceCrop = other.mSourceCrop;
            this.mFullDisplay = other.mFullDisplay;
            this.mDisplayId = other.mDisplayId;
            this.mScaleIndex = other.mScaleIndex;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (this.mTasks != null) {
            dest.writeInt(this.mTasks.length);
            dest.writeIntArray(this.mTasks);
        } else {
            dest.writeInt(0);
        }
        if (this.mLayer != null) {
            dest.writeInt(1);
            dest.writeTypedObject(this.mLayer, 0);
        } else {
            dest.writeInt(0);
        }
        if (this.mExcludeLayers != null) {
            dest.writeInt(this.mExcludeLayers.length);
            dest.writeTypedArray(this.mExcludeLayers, 0);
        } else {
            dest.writeInt(0);
        }
        dest.writeBinderArray(this.mExcludeWindows);
        dest.writeParcelable(this.mSourceCrop, 0);
        dest.writeBoolean(this.mFullDisplay);
        dest.writeInt(this.mDisplayId);
        dest.writeFloat(this.mScaleIndex);
    }
}
