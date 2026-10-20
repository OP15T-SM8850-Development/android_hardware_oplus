package com.oplus.wrapper.hardware.camera2;

import com.oplus.wrapper.hardware.camera2.impl.CameraMetadataNative;
import java.lang.reflect.Field;

public class CameraCharacteristics {
    private final android.hardware.camera2.CameraCharacteristics mCharacteristics;

    public CameraCharacteristics(android.hardware.camera2.CameraCharacteristics characteristics) {
        this.mCharacteristics = characteristics;
    }

    public CameraMetadataNative getNativeMetadata() {
        if (mCharacteristics == null) {
            return new CameraMetadataNative(null);
        }
        try {
            Field field = null;
            Class<?> clazz = mCharacteristics.getClass();
            while (clazz != null && field == null) {
                try {
                    field = clazz.getDeclaredField("mProperties");
                } catch (NoSuchFieldException e) {
                    clazz = clazz.getSuperclass();
                }
            }
            if (field != null) {
                field.setAccessible(true);
                return new CameraMetadataNative(field.get(mCharacteristics));
            }
        } catch (Throwable t) {
            // fallback
        }
        return new CameraMetadataNative(null);
    }
}
