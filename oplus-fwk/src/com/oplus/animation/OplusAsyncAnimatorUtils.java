/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.animation;

import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * UI-thread port of the OEM RenderThread-side async view mutator utility
 * (OOS16.0.8 oplus-framework.jar). Every OEM method stages a property on the
 * view's RenderNode via hidden android.view.OplusRenderNodeAnimator natives;
 * the public-API equivalent is the same property set through the ordinary
 * View/RenderNode setters on the UI thread — which is where the feeding
 * springs/animators run on LineageOS (see com.oplus.view.OplusRenderNodeAnimator).
 * All shipped callers (OppoGallery2 incl. bundled COUI) discard the boolean
 * and rely purely on the side effect; return true where the property is
 * applied, false where only an OEM-only native could apply it.
 */
public class OplusAsyncAnimatorUtils {

    private OplusAsyncAnimatorUtils() {
    }

    public static boolean offsetLeftAndRight(View view, int offset) {
        // OEM: nOffsetLeftAndRight on the render node.
        view.offsetLeftAndRight(offset);
        return true;
    }

    public static boolean offsetTopAndBottom(View view, int offset) {
        // OEM: nOffsetTopAndBottom on the render node.
        view.offsetTopAndBottom(offset);
        return true;
    }

    public static boolean setAlpha(RenderNode renderNode, float alpha) {
        // OEM: nSetAlpha(renderNode.mNativeRenderNode, alpha). The public
        // RenderNode.setAlpha stages the same value; OppoGallery2's own non-RT
        // branch (PhotoBackgroundDrawable.setForegroundAlpha) uses exactly this
        // call on the same RenderNode.
        return renderNode.setAlpha(alpha);
    }

    public static boolean setAlpha(View view, float alpha) {
        // OEM: syncs the UI-side alpha then nSetAlpha on the render node; the
        // public View.setAlpha performs both (field + render-node push +
        // invalidation). Same call COUI's bundled NormalAnimationExecutor makes.
        view.setAlpha(alpha);
        return true;
    }

    public static boolean setBackgroundRenderEffect(View view, RenderEffect renderEffect) {
        // OEM-only native (nSetBackgroundRenderEffect): applies a RenderEffect to
        // the background layer only; AOSP has no background-only effect channel.
        // No shipped caller. Report "async apply unavailable".
        return false;
    }

    public static boolean setClipRect(View view, Rect rect) {
        // OEM: null rect -> nSetClipBoundsEmpty (clear clip), else
        // nSetClipBounds(l,t,r,b). View.setClipBounds(null) clears the clip —
        // same shape.
        view.setClipBounds(rect);
        return true;
    }

    public static boolean setClipToBounds(View view, boolean clipToBounds) {
        // OEM-only native (nSetClipToBounds): per-node clip-to-bounds flag with
        // no public per-View equivalent. No shipped caller. Report unavailable.
        return false;
    }

    public static boolean setElevation(View view, float elevation) {
        // OEM: nSetElevation on the render node.
        view.setElevation(elevation);
        return true;
    }

    public static boolean setLeftTopRightBottom(View view, int left, int top, int right, int bottom) {
        // OEM: nSetLeftTopRightBottom. View#setLeftTopRightBottom is
        // public-@hide (used by ObjectAnimator for bounds animation); available
        // because oplus-fwk compiles against full platform APIs.
        view.setLeftTopRightBottom(left, top, right, bottom);
        return true;
    }

    public static boolean setOutlineNone(View view) {
        // OEM: nSetOutlineNone on the render node.
        view.setOutlineProvider(null);
        return true;
    }

    public static boolean setOutlineRoundRect(View view, Rect rect, float radius, float alpha) {
        // OEM: nSetOutlineRoundRect(node, rect.l, rect.t, rect.r, rect.b,
        // radius, alpha). A ViewOutlineProvider integrates with clipToOutline
        // and shadows identically; setOutlineProvider triggers
        // invalidateOutline() itself. The rect is copied because the caller
        // (PhotoClipBoundTransitionView) mutates it per frame.
        final Rect bounds = new Rect(rect);
        final float outlineRadius = radius;
        final float outlineAlpha = alpha;
        view.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View v, Outline outline) {
                outline.setRoundRect(bounds, outlineRadius);
                outline.setAlpha(outlineAlpha);
            }
        });
        return true;
    }

    public static boolean setOutlineSmoothRoundRect(View view, Rect rect, float radius, float alpha, float weight) {
        // OEM-only native (nSetOutlineSmoothRoundRect): "smooth corner"
        // (squircle) outline — the smoothing weight has no AOSP counterpart.
        // Param order fixed to the OEM (radius, alpha, weight); the old stub
        // declared (radius, weight, alpha). No shipped caller. Report
        // unavailable.
        return false;
    }

    public static boolean setOutlineSpotShadowColor(View view, int color) {
        // OEM: nSetSpotShadowColor on the render node.
        view.setOutlineSpotShadowColor(color);
        return true;
    }

    public static boolean setRenderEffect(View view, RenderEffect renderEffect) {
        // OEM: nSetRenderEffect on the render node.
        view.setRenderEffect(renderEffect);
        return true;
    }

    public static boolean setRotation(View view, float rotation) {
        // OEM: nSetRotation on the render node.
        view.setRotation(rotation);
        return true;
    }

    public static boolean setRotationX(View view, float rotationX) {
        // OEM: nSetRotationX on the render node.
        view.setRotationX(rotationX);
        return true;
    }

    public static boolean setRotationY(View view, float rotationY) {
        // OEM: nSetRotationY on the render node.
        view.setRotationY(rotationY);
        return true;
    }

    public static boolean setScaleX(View view, float scaleX) {
        // OEM: nSetScaleX on the render node; COUI's bundled non-RT fallback
        // (NormalAnimationExecutor) uses the plain View setter.
        view.setScaleX(scaleX);
        return true;
    }

    public static boolean setScaleY(View view, float scaleY) {
        // OEM: nSetScaleY on the render node.
        view.setScaleY(scaleY);
        return true;
    }

    public static boolean setTranslationX(View view, float translationX) {
        // OEM: nSetTranslationX on the render node.
        view.setTranslationX(translationX);
        return true;
    }

    public static boolean setTranslationY(View view, float translationY) {
        // OEM: nSetTranslationY on the render node.
        view.setTranslationY(translationY);
        return true;
    }

    public static boolean setTranslationZ(View view, float translationZ) {
        // OEM: nSetTranslationZ on the render node.
        view.setTranslationZ(translationZ);
        return true;
    }
}
