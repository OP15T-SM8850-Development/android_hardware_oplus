package com.oplus.wrapper.hardware.camera2;

import com.oplus.wrapper.hardware.camera2.impl.CameraMetadataNative;
import java.lang.reflect.Field;

public class CaptureResult {
    private final android.hardware.camera2.CaptureResult mResult;

    public CaptureResult(android.hardware.camera2.CaptureResult result) {
        this.mResult = result;
    }

    public CameraMetadataNative getNativeMetadata() {
        if (mResult == null) {
            return new CameraMetadataNative(null);
        }
        try {
            Field field = null;
            Class<?> clazz = mResult.getClass();
            while (clazz != null && field == null) {
                try {
                    field = clazz.getDeclaredField("mResults");
                } catch (NoSuchFieldException e) {
                    clazz = clazz.getSuperclass();
                }
            }
            if (field != null) {
                field.setAccessible(true);
                return new CameraMetadataNative(field.get(mResult));
            }
        } catch (Throwable t) {
            // fallback
        }
        return new CameraMetadataNative(null);
    }
}
