package com.oplus.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/*
 * Stub implementation — OplusCanvas is an OEM (ColorOS/OOS) wrapper that draws
 * "smooth" (G2-continuous) rounded rects via a proprietary IBaseCanvasExt hook in
 * the OEM oplus-framework.jar, which is NOT shipped on LineageOS. The OnePlus
 * camera/gallery wrap a platform Canvas here and call drawSmoothRoundRect(...).
 * With no OEM smooth-corner backend on LOS we degrade gracefully to the standard
 * Canvas.drawRoundRect(...) so the corners render as ordinary (non-G2) rounded
 * rects instead of dying with NoClassDefFoundError. Mirrors the existing
 * com/oplus/graphics/Oplus* stub style and the OOS class shape
 * (implements IOplusCanvas; degrade path mirrors OplusOutlineSmooth).
 *
 * Surface referenced by com.oplus.camera + com.oplus.gallery2 (RE'd from shipped dex):
 *   <init>(Landroid/graphics/Canvas;)V
 *   drawSmoothRoundRect(FFFFFFLandroid/graphics/Paint;F)V
 *   drawSmoothRoundRect(Landroid/graphics/RectF;FFLandroid/graphics/Paint;F)V
 */
public class OplusCanvas implements IOplusCanvas {
    private static final String TAG = "OplusCanvas";

    private final Canvas mCanvas;

    public OplusCanvas(Canvas canvas) {
        this.mCanvas = canvas;
    }

    @Override
    public void drawSmoothRoundRect(float left, float top, float right, float bottom,
            float rx, float ry, Paint paint) {
        if (mCanvas != null) {
            mCanvas.drawRoundRect(left, top, right, bottom, rx, ry, paint);
        }
    }

    @Override
    public void drawSmoothRoundRect(float left, float top, float right, float bottom,
            float rx, float ry, Paint paint, float weight) {
        // 'weight' is the OEM G2 smoothness factor; ignored on LOS (no smooth backend).
        if (mCanvas != null) {
            mCanvas.drawRoundRect(left, top, right, bottom, rx, ry, paint);
        }
    }

    @Override
    public void drawSmoothRoundRect(RectF rect, float rx, float ry, Paint paint) {
        if (rect != null) {
            drawSmoothRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, paint);
        }
    }

    @Override
    public void drawSmoothRoundRect(RectF rect, float rx, float ry, Paint paint, float weight) {
        if (rect != null) {
            drawSmoothRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, paint, weight);
        }
    }
}
