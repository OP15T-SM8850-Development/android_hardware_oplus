package com.oplus.wrapper.hardware.camera2.impl;

import java.lang.reflect.Field;

public class CameraMetadataNative {
    private final Object mNativeObj;

    public CameraMetadataNative(Object nativeObj) {
        this.mNativeObj = nativeObj;
    }

    public long getMetadataPtr() {
        if (mNativeObj == null) {
            return 0L;
        }
        try {
            Field field = mNativeObj.getClass().getDeclaredField("mMetadataPtr");
            field.setAccessible(true);
            return field.getLong(mNativeObj);
        } catch (Throwable t) {
            return 0L;
        }
    }
}
