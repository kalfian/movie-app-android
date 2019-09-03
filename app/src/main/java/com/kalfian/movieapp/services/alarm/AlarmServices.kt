package com.kalfian.movieapp.services.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service

import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.kalfian.movieapp.services.StaticData
import com.pixplicity.easyprefs.library.Prefs
import java.util.*

class AlarmServices : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        Log.d("DATE_SELECTED", "ALARM SERVICE RUN")

        val isDailyAlarmOn: Boolean = Prefs.getBoolean(StaticData.DAILY_ALARM, true)
        val isReleaseTodayAlarmOn: Boolean = Prefs.getBoolean(StaticData.TODAY_MOVIE_ALARM, true)

        settingDailyAlarm(isDailyAlarmOn)
        settingReleaseTodayAlarm(isReleaseTodayAlarmOn)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    private fun settingDailyAlarm(isOn: Boolean) {
        if (isOn) {
            val mAlarm: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            // Get Time
            val c = Calendar.getInstance()
            val cDaily = Calendar.getInstance()

            c.set(Calendar.HOUR_OF_DAY, 7)
            c.set(Calendar.MINUTE, 0)
            c.set(Calendar.SECOND, 0)
            c.set(Calendar.MILLISECOND, 0)

            if (cDaily.after(c)) {
                c.add(Calendar.DATE, 1)
            }

            val i = Intent(this, AlarmDailyReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 0, i, 0)

            mAlarm.setRepeating(
                AlarmManager.RTC_WAKEUP,
                c.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }

    private fun settingReleaseTodayAlarm(isOn: Boolean) {

    }

}