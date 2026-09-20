/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.view;

/**
 * RenderThread animation target surface consumed by OppoGallery2 (COUIRtAnimationImpl).
 * The OEM declares these members on com.oplus.view.ITarget with IRtAnimationTarget as an
 * empty sub-interface; nothing in shipped consumers references ITarget, so the surface is
 * flattened here. Default bodies (including exception messages) mirror the OEM ITarget.
 */
public interface IRtAnimationTarget {

    void start();

    void end();

    void cancel();

    boolean doFrame(long frameTime);

    boolean isRunning();

    default void animateToFinalPosition(float finalPosition) {
        throw new RuntimeException("Only SpringAnimation can skipToEnd, but "
                + getClass().getName());
    }

    default void skipToEnd() {
        throw new RuntimeException("Only SpringAnimation can skipToEnd, but "
                + getClass().getName());
    }

    default void reverse() {
        throw new RuntimeException("Only animator can reverse, but " + getClass().getName());
    }

    default void setAnimationHandler() {
        throw new RuntimeException("animationHandler should be provided by target");
    }

    default void setStartDelay(long startDelay) {
        throw new RuntimeException("startDelay should be provided by target");
    }

    default long getStartDelay() {
        throw new RuntimeException("startDelay should be provided by target");
    }

    default void setDuration(long duration) {
        throw new RuntimeException("duration should be provided by target");
    }

    default long getDuration() {
        throw new RuntimeException("duration should be provided by target");
    }

    default long getTotalDuration() {
        throw new RuntimeException("totalDuration should be provided by target");
    }
}
