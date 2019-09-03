package com.kalfian.movieapp.services.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MainReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val intent = Intent(context, AlarmServices::class.java)
        context?.startService(intent)
    }
}