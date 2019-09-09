package com.kalfian.movieapp.services.alarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.kalfian.movieapp.R
import com.kalfian.movieapp.main.MainActivity
import com.kalfian.movieapp.services.StaticData

class AlarmReleaseReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context.let {
            val notificationBuilder = NotificationCompat.Builder(it!!, StaticData.channelID)

            val titleMovie : String? = it.resources?.getString(R.string.release_today_title)
            var extra = intent?.getStringExtra(StaticData.dataMovie).toString()

            if (extra == "") {
                extra = it.resources?.getString(R.string.no_movie_found).toString()
            }

            val i = Intent(it, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(it, 0, i, FLAG_ONE_SHOT)

            notificationBuilder.setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ico_movie)
                .setLargeIcon(BitmapFactory.decodeResource(it.resources, R.drawable.ico_movie))
                .setContentTitle(titleMovie)
                .setContentText(extra)
                .setDefaults(Notification.DEFAULT_LIGHTS.or(Notification.DEFAULT_SOUND))
                .setContentInfo(it.resources?.getString(R.string.information))

            val notificationManager: NotificationManager = it.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channel = NotificationChannel(StaticData.channelID, StaticData.channelName, NotificationManager.IMPORTANCE_DEFAULT)

                notificationBuilder.setChannelId(StaticData.channelID)

                notificationManager.createNotificationChannel(channel)
            }

            val notification = notificationBuilder.build()

            notificationManager.notify(StaticData.NOTIFICATION_ID, notification)
        }

    }
}