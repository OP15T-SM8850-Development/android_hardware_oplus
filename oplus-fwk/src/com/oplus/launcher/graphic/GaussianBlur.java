package com.oplus.launcher.graphic;

import android.content.Context;
import android.graphics.Bitmap;

/*
 * Stub implementation — com.oplus.launcher.graphic.GaussianBlur is an OEM
 * (ColorOS/OOS) launcher graphics helper that produces a Gaussian-blurred copy of a
 * Bitmap. It is NOT defined in any jar/apk we ship (it is referenced-only even inside
 * OplusLauncher.apk on the OEM dump); it is hard-referenced by the heytap addon
 * wrapper com.heytap.addon.launcher.graphic.GaussianBlur bundled inside
 * OppoGallery2.apk. That wrapper's getInstance() does an invoke-static on THIS class
 * and stores the result in an instance field, then dispatches every blur call to it —
 * so a missing class here is a class-load NoClassDefFoundError, and a null getInstance()
 * would NPE on the following instance call. This restores the API surface. With no OEM
 * blur backend on LineageOS the transform is a no-op: generate* returns the SOURCE
 * bitmap unchanged (a valid non-null Bitmap — un-blurred rather than crashing).
 * Mirrors the com/oplus/graphics/Oplus* stub style in this tree.
 *
 * Surface required by OppoGallery2 (RE'd from the shipped OppoGallery2 dex /
 * heytap addon wrapper):
 *   getInstance()Lcom/oplus/launcher/graphic/GaussianBlur;    (static)
 *   setScreenWidth(Landroid/content/Context;)V                (static, no-op)
 *   generateGaussianBitmap(Landroid/graphics/Bitmap;IFZ)Landroid/graphics/Bitmap;
 *   generateGaussianBitmap(Landroid/graphics/Bitmap;IFZZ)Landroid/graphics/Bitmap;
 *   generateGaussianBitmap(Landroid/graphics/Bitmap;Z)Landroid/graphics/Bitmap;
 *   generateGaussianBitmapTask(Landroid/graphics/Bitmap;IFZZ)Landroid/graphics/Bitmap;
 */
public class GaussianBlur {
    private static final String TAG = "GaussianBlur";
    private static volatile GaussianBlur sInstance;

    public GaussianBlur() {
    }

    public static GaussianBlur getInstance() {
        if (sInstance == null) {
            synchronized (GaussianBlur.class) {
                if (sInstance == null) {
                    sInstance = new GaussianBlur();
                }
            }
        }
        return sInstance;
    }

    public static void setScreenWidth(Context context) {
        // no-op (no OEM blur backend on LOS)
    }

    public Bitmap generateGaussianBitmap(Bitmap source, int radius, float scale, boolean recycle) {
        // no OEM Gaussian backend on LOS: return the source unchanged (un-blurred, non-null)
        return source;
    }

    public Bitmap generateGaussianBitmap(Bitmap source, int radius, float scale, boolean recycle, boolean async) {
        return source;
    }

    public Bitmap generateGaussianBitmap(Bitmap source, boolean recycle) {
        return source;
    }

    public Bitmap generateGaussianBitmapTask(Bitmap source, int radius, float scale, boolean recycle, boolean async) {
        return source;
    }
}
