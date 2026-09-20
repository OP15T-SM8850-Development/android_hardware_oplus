package com.oplus.graphics;

import android.graphics.Paint;
import android.graphics.RectF;

/*
 * Stub — OEM interface implemented by com.oplus.graphics.OplusCanvas in the
 * proprietary oplus-framework.jar (NOT shipped on LineageOS). No shipped consumer
 * references this interface directly (they use OplusCanvas concretely), but it is
 * declared here so OplusCanvas can faithfully `implements` it, mirroring the OOS
 * class shape and closing the type set. Mirrors the existing IOplusOutline /
 * IOplusPath stub style in this package.
 */
public interface IOplusCanvas {
    void drawSmoothRoundRect(float left, float top, float right, float bottom,
            float rx, float ry, Paint paint);

    void drawSmoothRoundRect(float left, float top, float right, float bottom,
            float rx, float ry, Paint paint, float weight);

    void drawSmoothRoundRect(RectF rect, float rx, float ry, Paint paint);

    void drawSmoothRoundRect(RectF rect, float rx, float ry, Paint paint, float weight);
}
