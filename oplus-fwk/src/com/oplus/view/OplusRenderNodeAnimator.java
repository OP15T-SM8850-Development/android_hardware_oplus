/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.view;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * UI-thread port of the OEM RenderThread animator facade. Only the four statics referenced
 * by OppoGallery2 are provided. The OEM runs the supplied target/animator on the RenderThread
 * via a hidden native value animator that pulses ITarget#doFrame() every RT frame; LineageOS
 * has no such pump, so the same springs/animators run on their default (UI-thread,
 * Choreographer-driven) handlers instead — matching COUI's own bundled non-RenderThread
 * fallback (NormalAnimationExecutor).
 */
public class OplusRenderNodeAnimator {

    private OplusRenderNodeAnimator() {
    }

    public static void animateToFinalPosition(Animator animator, float value) {
        // OEM: instanceof android.view.OplusRenderValueAnimator -> state PENDING; the next
        // RenderThread frame invokes target.animateToFinalPosition(value). Net effect for the
        // shipped spring targets: the spring retargets and starts if not already running.
        if (animator instanceof RtTargetAnimator) {
            ((RtTargetAnimator) animator).animateToFinalPosition(value);
        }
    }

    public static Animator createRenderValueAnimator(Animator animator, View view) {
        // OEM wraps 'animator' in an ITarget adapter (AnimatorTarget) that delegates
        // start/end/cancel/isRunning/duration/delay straight back to 'animator' and pulses its
        // frames from the RenderThread. Without the RT pump, the exact functional equivalent
        // is the animator itself, self-driven by the UI-thread Choreographer: the sole shipped
        // caller attaches its listeners to 'animator' before wrapping, so every callback fires
        // natively. 'view' only selects the RT timeline on OEM and has no UI-thread analogue.
        return animator != null ? animator : ValueAnimator.ofFloat(0f, 1f);
    }

    public static Animator createRtAnimator(IRtAnimationTarget target, View view) {
        if (target == null || view == null) {
            // OEM parity: returns null when either argument is null.
            return null;
        }
        // Deliberate deviation from the OEM sequence: target.setAnimationHandler() is NOT
        // called. On OEM it parks the target's spring on a no-op handler (the RenderThread
        // pump takes over frame delivery); with no pump on LineageOS that would freeze the
        // spring forever. Skipping the call keeps the spring on its default self-driving
        // Choreographer handler, which is exactly what COUI's NormalAnimationExecutor does
        // when the RenderThread path is unavailable.
        return new RtTargetAnimator(target);
    }

    public static long getFrameNumber(Animator animator) {
        // OEM: RenderThread frame number of the native animator (0 until the first RT frame).
        // No RT frames exist here; the only shipped consumer is log/telemetry-only.
        return 0L;
    }

    /**
     * UI-thread bridge exposing the OEM wrapper's Animator surface over an IRtAnimationTarget.
     * OEM defers target calls to the next RenderThread frame; this bridge issues them
     * synchronously — same net effect, one frame earlier. Shipped consumers only use
     * start()/cancel()/isRunning() plus the static animateToFinalPosition().
     */
    private static final class RtTargetAnimator extends Animator {

        private final IRtAnimationTarget mTarget;
        private boolean mCanceled;

        RtTargetAnimator(IRtAnimationTarget target) {
            mTarget = target;
        }

        void animateToFinalPosition(float value) {
            mCanceled = false;
            mTarget.animateToFinalPosition(value);
        }

        @Override
        public void start() {
            mCanceled = false;
            mTarget.start();
        }

        @Override
        public void end() {
            // OEM parity: end() is ignored while in the CANCEL state
            // (android.view.OplusRenderValueAnimator#end, state != CANCEL guard).
            if (!mCanceled) {
                mTarget.end();
            }
        }

        @Override
        public void cancel() {
            mCanceled = true;
            mTarget.cancel();
        }

        @Override
        public boolean isRunning() {
            return mTarget.isRunning();
        }

        @Override
        public long getStartDelay() {
            return mTarget.getStartDelay();
        }

        @Override
        public void setStartDelay(long startDelay) {
            mTarget.setStartDelay(startDelay);
        }

        @Override
        public Animator setDuration(long duration) {
            mTarget.setDuration(duration);
            return this;
        }

        @Override
        public long getDuration() {
            return mTarget.getDuration();
        }

        @Override
        public long getTotalDuration() {
            return mTarget.getTotalDuration();
        }

        @Override
        public void setInterpolator(TimeInterpolator interpolator) {
            // OEM: interpolators do not apply to ITarget-driven springs; no shipped caller
            // sets one on this object.
        }
    }
}
