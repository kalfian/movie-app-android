package com.kalfian.movieapp.presenter.setting

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kalfian.movieapp.BuildConfig
import com.kalfian.movieapp.R
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.services.BaseAPI
import com.kalfian.movieapp.services.StaticData
import com.kalfian.movieapp.services.alarm.AlarmDailyReceiver
import com.kalfian.movieapp.services.alarm.AlarmReleaseReceiver
import com.kalfian.movieapp.view.SettingView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SettingPresenter(val view: SettingView.ViewSetting): SettingView.PresenterSetting{

    private val baseApi = BaseAPI.create()
    private var compositeDisposable: CompositeDisposable? = null

    override fun setDailyAlarm(isOn: Boolean, context: Context) {
        val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //set Time
        val calender = Calendar.getInstance()
        val calenderDaily = Calendar.getInstance()

        calender.set(Calendar.HOUR_OF_DAY, 7)
        calender.set(Calendar.MINUTE, 0)
        calender.set(Calendar.SECOND, 0)
        calender.set(Calendar.MILLISECOND, 0)

        if (calenderDaily.after(calender)) {
            calender.add(Calendar.DATE, 1)
        }

        val i = Intent(context, AlarmDailyReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, i, 0)

        if (isOn) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calender.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        } else {
            alarmManager.cancel(pendingIntent)
        }
    }

    override fun setReleaseTodayMovieAlarm(isOn: Boolean, context: Context, data: String) {

        val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //set Time
        val calender = Calendar.getInstance()
        val calenderDaily = Calendar.getInstance()

        calender.set(Calendar.HOUR_OF_DAY, 8)
        calender.set(Calendar.MINUTE, 0)
        calender.set(Calendar.SECOND, 0)
        calender.set(Calendar.MILLISECOND, 0)

        if (calenderDaily.after(calender)) {
            calender.add(Calendar.DATE, 1)
        }

        Log.d("STATUS_GET_RELEASE", data)

        val iRelease = Intent(context, AlarmReleaseReceiver::class.java)
        iRelease.putExtra(StaticData.dataMovie, data)
        val pendingIntent = PendingIntent.getBroadcast(context, UUID.randomUUID().hashCode(), iRelease, FLAG_UPDATE_CURRENT)

        if (isOn) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calender.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        } else {
            alarmManager.cancel(pendingIntent)
        }
    }

    override fun getReleaseToday(context: Context?) {
        view.showLoader()

        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()).toString()

        compositeDisposable = CompositeDisposable()
        compositeDisposable?.add(
            baseApi.movieReleaseToday(BuildConfig.MOVIE_API_KEY, today, today)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribeWith(
                    object : DisposableObserver<ResponseMovie>(){
                        override fun onComplete() {
                            Log.d("STATUS_GET_RELEASE", "Complete")
                            view.hideLoader()
                        }

                        override fun onNext(t: ResponseMovie) {
                            view.hideLoader()
                            context?.let {
                                if (t.results != null) {
                                    Log.d("STATUS_GET_RELEASE", "SUCCESS")
                                    val data = t.results as ArrayList<ResponseMovie.ResultMovie>
                                    val movieTitle = data.joinToString(limit = 10, separator = ", ") {
                                        it.title
                                    }
                                    setReleaseTodayMovieAlarm(true, it, movieTitle)
                                    view.hideLoader()
                                } else {
                                    Log.d("STATUS_GET_RELEASE", "FAILURE")
                                    setReleaseTodayMovieAlarm(true, it, it.getString(R.string.no_movie_found))
                                    view.hideLoader()
                                }
                            }
                        }

                        override fun onError(e: Throwable) {
                            Log.d("STATUS_GET_RELEASE", "Error data :"+e.localizedMessage)
                            context?.let {
                                setReleaseTodayMovieAlarm(true, it, it.getString(R.string.no_movie_found))
                            }
                            view.hideLoader()
                        }
                    }
                )
        )
    }

}