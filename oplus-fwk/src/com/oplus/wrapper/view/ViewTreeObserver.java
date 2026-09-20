package com.oplus.wrapper.view;

public class ViewTreeObserver {
    private final android.view.ViewTreeObserver mViewTreeObserver;

    public ViewTreeObserver(android.view.ViewTreeObserver viewTreeObserver) {
        this.mViewTreeObserver = viewTreeObserver;
    }

    public void addOnComputeInternalInsetsListener(OnComputeInternalInsetsListener listener) {
    }

    public void removeOnComputeInternalInsetsListener(OnComputeInternalInsetsListener listener) {
    }

    public static final class InternalInsetsInfo {
        public static final int TOUCHABLE_INSETS_REGION = 3;

        public InternalInsetsInfo() {
        }

        public android.graphics.Region getTouchableRegion() {
            return new android.graphics.Region();
        }

        public void setTouchableInsets(int val) {
        }
    }

    public interface OnComputeInternalInsetsListener {
        void onComputeInternalInsets(InternalInsetsInfo info);
    }
}
