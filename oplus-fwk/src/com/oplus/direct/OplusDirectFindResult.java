package com.oplus.direct;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Closure stub for the OEM Parcelable result payload referenced by OppoGallery2.
 * Faithful port of the OOS oplus-framework.jar data class (Bundle-backed).
 */
public class OplusDirectFindResult implements Parcelable {

    private static final String TAG = "OplusDirectFindResult";

    public static final String EXTRA_RESULT_TEXT = "result_text";
    public static final String EXTRA_NO_IDNAMES = "no_idnames";
    public static final String EXTRA_ERROR = "direct_find_error";

    public static final String ERROR_NO_VIEWROOT = "no_viewroot";
    public static final String ERROR_NO_VIEW = "no_view";
    public static final String ERROR_NO_MAINWIN = "no_mainwin";
    public static final String ERROR_NO_TEXT = "no_text";
    public static final String ERROR_UNKNOWN_CMD = "unknown_cmd";

    private final Bundle mBundle;

    public OplusDirectFindResult() {
        mBundle = new Bundle();
    }

    public OplusDirectFindResult(Parcel in) {
        mBundle = new Bundle();
        readFromParcel(in);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Result=");
        sb.append(mBundle);
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        mBundle.writeToParcel(out, flags);
    }

    public void readFromParcel(Parcel in) {
        mBundle.readFromParcel(in);
    }

    public Bundle getBundle() {
        return mBundle;
    }

    public static final Parcelable.Creator<OplusDirectFindResult> CREATOR =
            new Parcelable.Creator<OplusDirectFindResult>() {
        @Override
        public OplusDirectFindResult createFromParcel(Parcel in) {
            return new OplusDirectFindResult(in);
        }

        @Override
        public OplusDirectFindResult[] newArray(int size) {
            return new OplusDirectFindResult[size];
        }
    };
}
