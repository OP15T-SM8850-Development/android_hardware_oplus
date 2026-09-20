/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.coloros.deepthinker;

/**
 * Stub of the legacy ColorOS boot-classpath interface
 * com.coloros.deepthinker.IColorDeepThinkerManager.
 *
 * Referenced only as the return type of ColorFrameworkFactory.getColorDeepThinkerManager()
 * and as a constructor argument in OppoGallery2s (never-executed on Android 16) pre-R
 * branch. No shipped consumer invokes any method on it, so a marker interface closes the
 * descriptor. Not present in any OOS jar we ship; not owned by any other package group.
 */
public interface IColorDeepThinkerManager {
}
