package com.oplus.screenshot;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Faithful port of com.oplus.screenshot.OplusLongshotViewInfo.
 *
 * OppoGallery2 hard-references this class (new-instance, invoke-direct <init>,
 * and via the CREATOR when unparceling long-shot/long-screenshot view info).
 * Because it is a Parcelable, a missing class or CREATOR field would surface as
 * NoClassDefFoundError / NoSuchFieldError at unparcel time -- a java.lang.Error
 * that escapes catch(Exception) -- so the full Parcelable contract is provided.
 *
 * Ported byte-for-byte from the OOS16 oplus-framework.jar: OEM class is
 * `public final`, extends Object, implements android.os.Parcelable, carries a
 * single boolean state (mIsUnsupported) serialized as an int (1/0). All
 * semantics (default false, reset/setUnsupported toggles, read/write ==1 check)
 * mirror the OEM implementation exactly, so behavior is identical rather than a
 * neutered no-op.
 *
 * Distinct from the co-located OplusLongshowViewInfo ("Longshow") stub, which is
 * a different type referenced by OplusLongshotViewBase.
 */
public final class OplusLongshotViewInfo implements Parcelable {

    public static final Parcelable.Creator<OplusLongshotViewInfo> CREATOR =
            new Parcelable.Creator<OplusLongshotViewInfo>() {
                @Override
                public OplusLongshotViewInfo createFromParcel(Parcel in) {
                    return new OplusLongshotViewInfo(in);
                }

                @Override
                public OplusLongshotViewInfo[] newArray(int size) {
                    return new OplusLongshotViewInfo[size];
                }
            };

    private boolean mIsUnsupported = false;

    public OplusLongshotViewInfo() {
    }

    public OplusLongshotViewInfo(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public boolean isUnsupported() {
        return mIsUnsupported;
    }

    public void readFromParcel(Parcel in) {
        mIsUnsupported = in.readInt() == 1;
    }

    public void reset() {
        mIsUnsupported = false;
    }

    public void setUnsupported() {
        mIsUnsupported = true;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mIsUnsupported ? 1 : 0);
    }
}
