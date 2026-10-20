package android.hardware.camera2;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.content.Context;
import android.os.IBinder;

public interface IOplusCameraManager extends IOplusCommonFeature {
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusCameraManager;
    }

    default IOplusCommonFeature getDefault() {
        return OplusCameraManager.getInstance();
    }

    enum Cmd {
        CMD_NONE,
        CMD_PRE_CAPTURE,
        CMD_PRE_OPEN,
        CMD_PRE_EVLIST,
        CMD_READ_MEM,
        CMD_PRE_PRO_MANUAL_WB,
        CMD_UI_EVENT,
        CMD_PRE_CLOSE,
        CMD_CAMERA_CONTROL,
        CMD_OPLUS_SEND_RUS_CONFIG_TO_HAL,
        CMD_OPLUS_FORCE_FALLBACK,
        CMD_OPLUS_FORCE_EIS,
        CMD_OPLUS_OPERATION_MODE,
        CMD_OPLUS_PRO_COLOR,
        CMD_OPLUS_FORCE_FPS,
        CMD_OPLUS_HCS,
        CMD_OPLUS_IO_PRIORITY,
        CMD_OPLUS_FORCE_DCG,
        CMD_OPLUS_FORCE_AINR;
    }

    default int sendOplusExtCamCmd(CaptureRequest.Builder builder, Cmd cmd, int... args) {
        return 0;
    }

    default int sendOplusExtCamCmd(CaptureRequest.Builder builder, int cmd, int... args) {
        return 0;
    }

    default void sendOplusExtCamCmd(Context context, Cmd cmd, int... args) {
    }

    default boolean isAuthedClient(boolean z) {
        return true;
    }

    default boolean isAuthedClient(Context context) {
        return true;
    }

    default boolean isPrivilegedApp(String str) {
        return true;
    }

    default void setDeathRecipient(IBinder binder) {
    }

    default void setCallInfo() {
    }

    default void preOpenCamera(Context context) {
    }
}
