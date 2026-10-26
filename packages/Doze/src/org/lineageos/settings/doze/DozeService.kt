/*
 * Copyright (C) 2021-2022 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.doze

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import android.provider.Settings
import androidx.preference.PreferenceManager
import android.os.IBinder
import android.util.Log

class DozeService : Service() {
    private lateinit var pickupSensor: PickupSensor
    private lateinit var pocketSensor: PocketSensor
    private var destroyed = false
    private lateinit var preferences: SharedPreferences
    private val settingsObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) { updateSensors() }
    }
    private val preferencesListener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
        updateSensors()
    }

    private val screenStateReceiver =
        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    Intent.ACTION_SCREEN_ON -> disableSensors()
                    Intent.ACTION_SCREEN_OFF -> updateSensors()
                }
            }
        }

    override fun onCreate() {
        Log.d(TAG, "Creating service")
        pickupSensor =
            PickupSensor(
                this,
                resources.getString(R.string.pickup_sensor_type),
                resources.getFloat(R.dimen.pickup_sensor_value),
            )
        pocketSensor =
            PocketSensor(
                this,
                resources.getString(R.string.pocket_sensor_type),
                resources.getFloat(R.dimen.pocket_sensor_value),
            )

        val screenStateFilter = IntentFilter()
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON)
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(screenStateReceiver, screenStateFilter)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        preferences.registerOnSharedPreferenceChangeListener(preferencesListener)
        contentResolver.registerContentObserver(
            Settings.Secure.getUriFor(Settings.Secure.DOZE_ENABLED), false, settingsObserver)
        contentResolver.registerContentObserver(
            Settings.Secure.getUriFor(Settings.Secure.DOZE_ALWAYS_ON), false, settingsObserver)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        updateSensors()
        return START_STICKY
    }

    override fun onDestroy() {
        destroyed = true
        super.onDestroy()

        unregisterReceiver(screenStateReceiver)
        preferences.unregisterOnSharedPreferenceChangeListener(preferencesListener)
        contentResolver.unregisterContentObserver(settingsObserver)
        disableSensors()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun disableSensors() {
        pickupSensor.disable()
        pocketSensor.disable()
    }

    private fun updateSensors() {
        if (destroyed) return
        val canListen = !getSystemService(PowerManager::class.java)!!.isInteractive &&
            Utils.isDozeEnabled(this) && !Utils.isAlwaysOnEnabled(this)
        if (canListen && Utils.isPickUpEnabled(this)) pickupSensor.enable()
        else pickupSensor.disable()
        if (canListen && Utils.isPocketEnabled(this)) pocketSensor.enable()
        else pocketSensor.disable()
    }

    companion object {
        private const val TAG = "DozeService"
    }
}
