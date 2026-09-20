package android.os;

import android.content.Context;
import android.view.KeyEvent;

/**
 * LOS shim for the OEM android.os.OplusKeyEventManager. The LOS oplus-fwk previously carried only
 * the OnKeyEventObserver interface; the OEM apps (e.g. OppoGallery2) also call getInstance() +
 * register/unregisterKeyEventObserver(). The OEM impl pulls OplusActivityTaskManager + a chain of
 * OEM classes absent from LOS, so this provides getInstance() returning a singleton whose
 * key-event registration is a no-op (the key-listening feature degrades silently rather than
 * crashing).
 */
public class OplusKeyEventManager {
    public static final String TAG = "OplusKeyEventManager";

    public static final int INTERCEPT_ALWAYS = 0;
    public static final int INTERCEPT_ONCE = 1;

    public static final int LISTEN_ALL_KEY_EVENT = 0;
    public static final int LISTEN_POWER_KEY_EVENT = 1;
    public static final int LISTEN_VOLUME_UP_KEY_EVENT = 2;
    public static final int LISTEN_VOLUME_DOWN_KEY_EVENT = 4;
    public static final int LISTEN_MENU_KEY_EVENT = 8;
    public static final int LISTEN_HOME_KEY_EVENT = 16;
    public static final int LISTEN_BACK_KEY_EVENT = 32;
    public static final int LISTEN_F4_KEY_EVENT = 64;
    public static final int LISTEN_CAMERA_KEY_EVENT = 128;
    public static final int LISTEN_HEADSETHOOK_KEY_EVENT = 1024;
    public static final int LISTEN_VOLUME_MUTE_KEY_EVENT = 2048;
    public static final int LISTEN_APP_SWITCH_KEY_EVENT = 4096;
    public static final int LISTEN_WAKEUP_KEY_EVENT = 8192;
    public static final int LISTEN_BRIGHTNESS_UP_KEY_EVENT = 16384;
    public static final int LISTEN_BRIGHTNESS_DOWN_KEY_EVENT = 32768;
    public static final int LISTEN_ENDCALL_KEY_EVENT = 65536;
    public static final int LISTEN_SLEEP_KEY_EVENT = 131072;
    public static final int LISTEN_ACTION_BUTTON_SINGLE_TAP_KEY_EVENT = 262144;
    public static final int LISTEN_ACTION_BUTTON_LONG_PRESS_KEY_EVENT = 524288;
    public static final int LISTEN_LINGXI_GAME_KEY_EVENT = 16777216;
    public static final int LISTEN_SHOULDER_DOWN_KEY_EVENT = 33554432;
    public static final int LISTEN_SHOULDER_UP_KEY_EVENT = 67108864;
    public static final int LISTEN_LINGXI_NO_GAME_KEY_EVENT = 134217728;

    public int mVersion = 1;

    private static volatile OplusKeyEventManager sInstance;

    public interface OnKeyEventObserver {
        void onKeyEvent(KeyEvent event);
    }

    private OplusKeyEventManager() {
    }

    public static OplusKeyEventManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusKeyEventManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusKeyEventManager();
                }
            }
        }
        return sInstance;
    }

    public boolean registerKeyEventObserver(Context context, OnKeyEventObserver observer, int listenFlag) {
        return false;
    }

    public boolean unregisterKeyEventObserver(Context context, OnKeyEventObserver observer) {
        return false;
    }

    public int getVersion() {
        return mVersion;
    }

    public boolean registerKeyEventInterceptor(Context context, String key, OnKeyEventObserver observer, android.util.ArrayMap map) {
        return false;
    }

    public boolean unregisterKeyEventInterceptor(Context context, String key, OnKeyEventObserver observer) {
        return false;
    }
}
