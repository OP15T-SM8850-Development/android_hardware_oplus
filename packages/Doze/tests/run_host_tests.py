#!/usr/bin/env python3
"""Exercise real DozeService, Utils and gesture listeners using host Android fakes."""
from pathlib import Path
import subprocess, tempfile, os
ROOT=Path(__file__).resolve().parents[1]
TREE=ROOT.parents[3]
S={
'os.kt':"""package android.os
class IBinder
class Looper {companion object {fun getMainLooper()=Looper()}}
class Handler(l:Looper)
object SystemClock {var now=10000L;fun elapsedRealtime()=now;fun uptimeMillis()=now}
class UserHandle(val id:Int){companion object {const val USER_CURRENT=0;val CURRENT=UserHandle(0)}}
class PowerManager {var isInteractive=false;var wakes=0;var locks=0
 fun newWakeLock(flags:Int,tag:String)=WakeLock()
 inner class WakeLock {var isHeld=false;fun setReferenceCounted(b:Boolean){};fun acquire(timeout:Long){isHeld=true;locks++};fun release(){isHeld=false;locks--}}
 fun wakeUp(time:Long,reason:Int,tag:String,display:Int){wakes++}
 companion object {const val PARTIAL_WAKE_LOCK=1;const val WAKE_REASON_GESTURE=1}}
""",
'sensors.kt':"""package android.hardware
class Sensor(val stringType:String,val isWakeUpSensor:Boolean) {companion object {const val TYPE_ALL=0}}
class SensorEvent(val values:FloatArray)
interface SensorEventListener {fun onSensorChanged(e:SensorEvent);fun onAccuracyChanged(s:Sensor,a:Int)}
class SensorManager {var sensors=listOf(Sensor("pickup",false),Sensor("pickup",true),Sensor("pocket",true));val listeners=linkedMapOf<SensorEventListener,Sensor>();var registrations=0
 fun getSensorList(type:Int)=sensors
 fun registerListener(l:SensorEventListener,s:Sensor,rate:Int):Boolean {listeners[l]=s;registrations++;return true}
 fun unregisterListener(l:SensorEventListener,s:Sensor?=null){listeners.remove(l)}
 companion object {const val SENSOR_DELAY_NORMAL=1}}
""",
'content.kt':"""package android.content
import android.os.*
import android.hardware.*
import android.database.ContentObserver
class Intent(val action:String?=null){constructor(c:Context,cls:Class<*>):this(null)
 companion object {const val ACTION_SCREEN_ON="on";const val ACTION_SCREEN_OFF="off"}}
class IntentFilter {fun addAction(s:String){}}
abstract class BroadcastReceiver {abstract fun onReceive(c:Context,i:Intent)}
interface SharedPreferences {fun interface OnSharedPreferenceChangeListener {fun onSharedPreferenceChanged(p:SharedPreferences,k:String)}
 fun getBoolean(k:String?,d:Boolean):Boolean;fun getString(k:String?,d:String?):String?
 fun registerOnSharedPreferenceChangeListener(l:OnSharedPreferenceChangeListener);fun unregisterOnSharedPreferenceChangeListener(l:OnSharedPreferenceChangeListener)}
class Preferences:SharedPreferences {val values=mutableMapOf<String,Any>();val listeners=mutableSetOf<SharedPreferences.OnSharedPreferenceChangeListener>()
 override fun getBoolean(k:String?,d:Boolean)=values[k] as? Boolean ?: d
 override fun getString(k:String?,d:String?)=values[k] as? String ?: d
 override fun registerOnSharedPreferenceChangeListener(l:SharedPreferences.OnSharedPreferenceChangeListener){listeners.add(l)}
 override fun unregisterOnSharedPreferenceChangeListener(l:SharedPreferences.OnSharedPreferenceChangeListener){listeners.remove(l)}
 fun set(k:String,v:Any){values[k]=v;listeners.toList().forEach{it.onSharedPreferenceChanged(this,k)}}}
class ContentResolver {val observers=mutableSetOf<ContentObserver>();fun registerContentObserver(uri:String,b:Boolean,o:ContentObserver){observers.add(o)};fun unregisterContentObserver(o:ContentObserver){observers.remove(o)};fun changed(){observers.toList().forEach{it.onChange(false)}}}
open class Context {val power=PowerManager();val sensors=SensorManager();val prefs=Preferences();val contentResolver=ContentResolver();val resources=android.content.res.Resources();var receiver:BroadcastReceiver?=null;var pulses=0
 @Suppress("UNCHECKED_CAST") fun <T> getSystemService(cls:Class<T>):T?=when(cls){PowerManager::class.java->power;SensorManager::class.java->sensors;else->null} as T?
 fun registerReceiver(r:BroadcastReceiver,f:IntentFilter){receiver=r};fun unregisterReceiver(r:BroadcastReceiver){receiver=null}
 fun startServiceAsUser(i:Intent,u:UserHandle){};fun stopServiceAsUser(i:Intent,u:UserHandle){};fun sendBroadcastAsUser(i:Intent,u:UserHandle){pulses++}}
""",
'res.kt':"""package android.content.res
class Resources {fun getString(id:Int)=if(id==1) "pickup" else "pocket";fun getFloat(id:Int)=0f}
""",
'service.kt':"""package android.app
import android.content.*
import android.os.IBinder
open class Service:Context(){open fun onCreate(){};open fun onStartCommand(i:Intent?,f:Int,id:Int)=0;open fun onDestroy(){};open fun onBind(i:Intent?):IBinder?=null;companion object {const val START_STICKY=1}}
""",
'observer.kt':"""package android.database
open class ContentObserver(h:android.os.Handler){open fun onChange(b:Boolean){}}
""",
'settings.kt':"""package android.provider
object Settings {object Secure {const val DOZE_ENABLED="doze";const val DOZE_ALWAYS_ON="aod";val values=mutableMapOf<String,Int>()
 fun getInt(c:android.content.ContentResolver,k:String,d:Int)=values[k]?:d
 fun getIntForUser(c:android.content.ContentResolver,k:String,d:Int,u:Int)=getInt(c,k,d)
 fun putInt(c:android.content.ContentResolver,k:String,v:Int):Boolean {values[k]=v;return true}
 fun putIntForUser(c:android.content.ContentResolver,k:String,v:Int,u:Int)=putInt(c,k,v)
 fun getUriFor(k:String)=k}}
""",
'preference.kt':"""package androidx.preference
object PreferenceManager {fun getDefaultSharedPreferences(c:android.content.Context):android.content.SharedPreferences=c.prefs}
""",
'view.kt':"""package android.view
object Display {const val DEFAULT_DISPLAY=0}
""",
'ambient.kt':"""package android.hardware.display
class AmbientDisplayConfiguration(c:android.content.Context){fun alwaysOnAvailable()=true}
""",
'log.kt':"""package android.util
object Log {fun d(t:String,s:String)=0}
""",
'R.kt':"""package org.lineageos.settings.doze
object R {object string {const val pickup_sensor_type=1;const val pocket_sensor_type=2};object dimen {const val pickup_sensor_value=1;const val pocket_sensor_value=2}}
""",
'Test.kt':"""package org.lineageos.settings.doze
import android.content.Intent
import android.hardware.SensorEvent
import android.os.SystemClock
import android.provider.Settings
fun main(){
 val s=DozeService();s.prefs.values[Utils.GESTURE_PICK_UP_KEY]="2";s.prefs.values[Utils.GESTURE_POCKET_KEY]=true
 s.onCreate();s.onStartCommand(null,0,0)
 check(s.sensors.listeners.size==2&&s.sensors.listeners.values.all{it.isWakeUpSensor})
 s.onStartCommand(null,0,0);check(s.sensors.registrations==2)
 SystemClock.now+=100000;check(s.power.wakes==0&&s.pulses==0) // Stationary: no event, no periodic work.
 val pickup=s.sensors.listeners.entries.first{it.value.stringType=="pickup"}.key
 pickup.onSensorChanged(SensorEvent(floatArrayOf(0f)));check(s.power.wakes==1)
 s.prefs.set(Utils.GESTURE_PICK_UP_KEY,"0")
 check(s.sensors.listeners.size==1&&s.power.locks==0)
 SystemClock.now+=3000;pickup.onSensorChanged(SensorEvent(floatArrayOf(0f)));check(s.power.wakes==1)
 s.prefs.set(Utils.GESTURE_PICK_UP_KEY,"1");check(s.sensors.listeners.size==2)
 SystemClock.now+=3000;pickup.onSensorChanged(SensorEvent(floatArrayOf(0f)));check(s.pulses==1&&s.power.wakes==1)
 s.power.isInteractive=true;s.receiver!!.onReceive(s,Intent(Intent.ACTION_SCREEN_ON));check(s.sensors.listeners.isEmpty())
 s.power.isInteractive=false;s.receiver!!.onReceive(s,Intent(Intent.ACTION_SCREEN_OFF));check(s.sensors.listeners.size==2)
 Settings.Secure.values[Settings.Secure.DOZE_ALWAYS_ON]=1;s.contentResolver.changed();check(s.sensors.listeners.isEmpty())
 Settings.Secure.values[Settings.Secure.DOZE_ALWAYS_ON]=0;s.contentResolver.changed();check(s.sensors.listeners.size==2)
 Settings.Secure.values[Settings.Secure.DOZE_ENABLED]=0;s.contentResolver.changed();check(s.sensors.listeners.isEmpty())
 Settings.Secure.values[Settings.Secure.DOZE_ENABLED]=1;s.contentResolver.changed();check(s.sensors.listeners.size==2)
 val lateObserver=s.contentResolver.observers.first();val latePreference=s.prefs.listeners.first()
 s.onDestroy();lateObserver.onChange(false);latePreference.onSharedPreferenceChanged(s.prefs,Utils.GESTURE_PICK_UP_KEY);check(s.sensors.listeners.isEmpty()&&s.prefs.listeners.isEmpty()&&s.contentResolver.observers.isEmpty())
 val c=android.content.Context();c.sensors.sensors=listOf(android.hardware.Sensor("pickup",false));val bad=PickupSensor(c,"pickup",0f);bad.enable();check(c.sensors.listeners.isEmpty())
 println("Doze gesture registration, settings changes, AOD, stale events, wake-up selection and teardown passed")
}
"""
}
with tempfile.TemporaryDirectory() as tmp:
 t=Path(tmp)
 for name,source in S.items():(t/name).write_text(source)
 sources=list(t.glob('*.kt'))+[ROOT/'src/org/lineageos/settings/doze'/n for n in ['PickupSensor.kt','PocketSensor.kt','DozeService.kt','Utils.kt']]
 env=dict(os.environ,JAVA_HOME=str(TREE/'prebuilts/jdk/jdk21/linux-x86'))
 result=subprocess.run([str(TREE/'external/kotlinc/bin/kotlinc'),*[str(p) for p in sources],'-nowarn','-include-runtime','-d',str(t/'test.jar')],env=env,capture_output=True,text=True)
 if result.returncode: print(result.stdout+result.stderr);raise SystemExit(result.returncode)
 subprocess.run([str(TREE/'prebuilts/jdk/jdk21/linux-x86/bin/java'),'-jar',str(t/'test.jar')],check=True)
