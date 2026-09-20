/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.animation;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;

/**
 * Stub for the OEM cross-activity "view seamless" shared-element transition
 * entry point. setSeamlessView reports failure (false) so callers fall back to
 * the standard activity transition. The nested AnimationCallback is ported
 * faithfully because callers subclass it and pass instances to setSeamlessView.
 */
public class OplusViewSeamless {

    public static final String BUNDLE_ALPHA_CROSSFADE_END_CLOSE = "view_seamless_alpha_crossfade_end_close";
    public static final String BUNDLE_ALPHA_CROSSFADE_END_OPEN = "view_seamless_alpha_crossfade_end_open";
    public static final String BUNDLE_ALPHA_OUT_ON_POSITION_CHANGE = "view_seamless_alpha_out_on_position_change";
    public static final String BUNDLE_ANIM_PARAM = "view_seamless_param";
    public static final String BUNDLE_BITMAP = "view_seamless_bitmap";
    public static final String BUNDLE_COLOR = "view_seamless_color";
    public static final String BUNDLE_DEST_ACTIVITY_ALPHA_START = "view_seamless_dest_activity_alpha_start";
    public static final String BUNDLE_DIM_EFFECT = "view_seamless_dim_effect";
    public static final String BUNDLE_FILL_BGCOLOR = "view_seamless_fill_bgcolor";
    public static final String BUNDLE_FORCE_LEASH_ALPHA_OUT = "view_seamless_force_leash_alpha_out";
    public static final String BUNDLE_LIST_COVER = "view_seamless_list_cover";
    public static final String BUNDLE_RADIUS = "view_seamless_radius";
    public static final String BUNDLE_RECT = "view_seamless_rect";
    public static final String BUNDLE_RUS_DATA = "view_seamless_rus_data";
    public static final String BUNDLE_SHADOW = "view_seamless_shadow_radius";
    public static final String BUNDLE_THIRD_PARTY = "view_seamless_third_party";
    public static final String BUNDLE_VIEW_VISIBLE = "view_seamless_view_visible";
    public static final String BUNDLE_VIEW_WITH_ALPHA = "view_seamless_view_with_alpha";
    public static final int OS_16_0_BASE = 0x9088;
    public static final int OS_16_1_BASE = 0x9470;
    public static final int OS_17_0_BASE = 0x9c40;
    public static final String REMOTE_DEBUG_NAME = "View Seamless";
    public static final int TRANSIT_FLAG_VIEW_SEAMLESS_REMOTE = 0x100000;
    public static final String VIEW_SEAMLESS_CLOSE = "view_seamless_close";
    public static final String VIEW_SEAMLESS_OPEN = "view_seamless_open";

    private OplusViewSeamless() {
    }

    public static boolean finishCurrentAnimation() {
        return false;
    }

    public static int getVersion() {
        return 0;
    }

    public static void setForceLeashAlphaOut(Activity activity, boolean force) {
    }

    public static boolean setSeamlessView(View view, Context context, Bundle bundle,
            OplusViewSeamless.AnimationCallback callback) {
        return false;
    }

    public static void setSkipViewSeamless(Activity activity) {
    }

    public static void skipBackAnim(Activity activity) {
    }

    public static class AnimationCallback {
        private PointF mOffset = new PointF();

        public AnimationCallback() {
        }

        public void animationProgress(float progress) {
        }

        public void onAnimationStart(boolean isOpen) {
        }

        public void onAnimationEnd(boolean isOpen) {
        }

        public void setPositionOffset(PointF offset) {
            this.mOffset = offset;
        }

        public PointF getPositionOffset() {
            return this.mOffset;
        }
    }
}
