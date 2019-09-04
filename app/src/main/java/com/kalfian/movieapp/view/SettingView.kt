package com.kalfian.movieapp.view

import android.content.Context

interface SettingView {
    interface ViewSetting {
        fun setDailyAlarm()
        fun setReleaseMovieToday()
        fun showLoader()
        fun hideLoader()
    }

    interface PresenterSetting {
        fun setDailyAlarm(isOn : Boolean, context: Context)
        fun setReleaseTodayMovieAlarm(isOn : Boolean, context: Context, data: String)
        fun getReleaseToday(context: Context?)
    }
}