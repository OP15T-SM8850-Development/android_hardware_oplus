package android.hardware.camera2;

import android.content.Context;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.marshal.MarshalRegistry;
import android.hardware.camera2.marshal.Marshaler;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class OplusCameraManager implements IOplusCameraManager {
    private static final String TAG = "OplusCameraManager";

    private static volatile OplusCameraManager sInstance;

    public static OplusCameraManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusCameraManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusCameraManager();
                }
            }
        }
        return sInstance;
    }

    @Override
    public int sendOplusExtCamCmd(CaptureRequest.Builder builder, Cmd cmd, int... args) {
        return 0;
    }

    @Override
    public int sendOplusExtCamCmd(CaptureRequest.Builder builder, int cmd, int... args) {
        return 0;
    }

    @Override
    public void sendOplusExtCamCmd(Context context, Cmd cmd, int... args) {
    }

    @Override
    public boolean isAuthedClient(boolean z) {
        return true;
    }

    @Override
    public boolean isAuthedClient(Context context) {
        return true;
    }

    @Override
    public boolean isPrivilegedApp(String str) {
        return true;
    }

    @Override
    public void setDeathRecipient(IBinder binder) {
    }

    @Override
    public void setCallInfo() {
    }

    @Override
    public void preOpenCamera(Context context) {
    }

    // The Oplus camera SDK reads capture metadata straight from the HAL meta buffer
    // (ApsTotalResult) and relies on these helpers to map standard keys to native tags
    // and to unmarshal the raw entry bytes. Returning 0/null here leaves APS without
    // ISO, exposure, AWB etc. for every frame, so mirror the stock implementation.
    public static int getMetadataTag(CaptureResult.Key<?> key) {
        if (key == null) {
            return -1;
        }
        return key.getNativeKey().getTag();
    }

    public static <T> T metaDataValueConvert(CaptureResult.Key<T> key, int nativeType,
            byte[] value) {
        if (key == null || value == null) {
            return null;
        }
        Marshaler<T> marshaler = MarshalRegistry.getMarshaler(
                key.getNativeKey().getTypeReference(), nativeType);
        return marshaler.unmarshal(ByteBuffer.wrap(value).order(ByteOrder.nativeOrder()));
    }

    public static Object getEmptyCameraMetadataNative(long[] metadataPtr) {
        CameraMetadataNative meta = new CameraMetadataNative();
        if (metadataPtr != null && metadataPtr.length > 0) {
            metadataPtr[0] = meta.getMetadataPtr();
        }
        return meta;
    }

    public static TotalCaptureResult generateTotalCaptureResult(Object meta, long frameNumber) {
        if (!(meta instanceof CameraMetadataNative)) {
            return null;
        }
        TotalCaptureResult result = new TotalCaptureResult((CameraMetadataNative) meta, 0);
        try {
            Field field = CaptureResult.class.getDeclaredField("mFrameNumber");
            field.setAccessible(true);
            field.setLong(result, frameNumber);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Log.w(TAG, "Unable to set frame number on generated result", e);
        }
        return result;
    }
}
