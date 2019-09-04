package com.kalfian.movieapp.view

import android.content.Context
import com.kalfian.movieapp.model.ResponseMovie

interface SettingView {
    interface ViewSetting {
        fun setDailyAlarm()
        fun setReleaseMovieToday()
        fun showLoader()
        fun hideLoader()
    }

    interface PresenterSetting {
        fun setDailyAlarm(isOn : Boolean, context: Context)
        fun setReleaseTodayMovieAlarm(isOn : Boolean, context: Context, data : ResponseMovie.ResultMovie?)
        fun getReleaseToday(context: Context?)
    }
}