/*
 * Copyright (C) 2021-2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.doze

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.SystemClock
import android.os.PowerManager
import android.util.Log

class PocketSensor(
    private val context: Context,
    sensorType: String,
    private val sensorValue: Float,
) : SensorEventListener {
    private val sensorManager = context.getSystemService(SensorManager::class.java)!!
    private val sensor = Utils.getSensor(sensorManager, sensorType, requireWakeUp = true)

    private var registered = false
    private var entryTimestamp = 0L

    override fun onSensorChanged(event: SensorEvent) {
        if (!registered || context.getSystemService(PowerManager::class.java)!!.isInteractive
            || !Utils.isPocketEnabled(context)
            || !Utils.isDozeEnabled(context) || Utils.isAlwaysOnEnabled(context)) return
        if (DEBUG) Log.d(TAG, "Got sensor event: ${event.values[0]}")
        val delta = SystemClock.elapsedRealtime() - entryTimestamp
        if (delta < MIN_PULSE_INTERVAL_MS) {
            return
        }
        entryTimestamp = SystemClock.elapsedRealtime()
        if (event.values[0] == sensorValue) {
            Utils.launchDozePulse(context)
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
    }

    companion object {
        private const val TAG = "PocketSensor"
        private const val DEBUG = false

        private const val MIN_PULSE_INTERVAL_MS = 2500L
    }
}
