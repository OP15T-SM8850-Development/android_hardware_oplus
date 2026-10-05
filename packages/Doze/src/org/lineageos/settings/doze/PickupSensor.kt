/*
 * Copyright (C) 2021-2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.doze

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.PowerManager
import android.os.SystemClock
import android.util.Log
import android.view.Display

class PickupSensor(
    private val context: Context,
    sensorType: String,
    private val sensorValue: Float,
) : SensorEventListener {
    private val powerManager = context.getSystemService(PowerManager::class.java)!!
    private val wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG).apply {
        setReferenceCounted(false)
    }

    private val sensorManager = context.getSystemService(SensorManager::class.java)!!
    private val sensor = Utils.getSensor(sensorManager, sensorType, requireWakeUp = true)

    private var registered = false
    private var entryTimestamp = 0L

    override fun onSensorChanged(event: SensorEvent) {
        if (!registered || !Utils.isPickUpEnabled(context) || powerManager.isInteractive
            || !Utils.isDozeEnabled(context) || Utils.isAlwaysOnEnabled(context)) return
        if (DEBUG) Log.d(TAG, "Got sensor event: ${event.values[0]}")
        val delta = SystemClock.elapsedRealtime() - entryTimestamp
        if (delta < MIN_PULSE_INTERVAL_MS) {
            return
        }
        entryTimestamp = SystemClock.elapsedRealtime()
        if (event.values[0] == sensorValue) {
            if (Utils.isPickUpSetToWake(context)) {
                wakeLock.acquire(WAKELOCK_TIMEOUT_MS)
                powerManager.wakeUp(
                    SystemClock.uptimeMillis(),
                    PowerManager.WAKE_REASON_GESTURE,
                    TAG,
                    Display.DEFAULT_DISPLAY,
                )
            } else {
                Utils.launchDozePulse(context)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    fun enable() {
        if (sensor == null || registered) return
        entryTimestamp = SystemClock.elapsedRealtime()
        registered = sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun disable() {
        registered = false
        sensorManager.unregisterListener(this)
        if (wakeLock.isHeld) wakeLock.release()
    }

    companion object {
        private const val TAG = "PickupSensor"
        private const val DEBUG = false

        private const val MIN_PULSE_INTERVAL_MS = 2500L
        private const val WAKELOCK_TIMEOUT_MS = 300L
    }
}
