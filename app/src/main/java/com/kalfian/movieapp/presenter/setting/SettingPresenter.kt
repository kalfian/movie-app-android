package com.kalfian.movieapp.presenter.setting

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kalfian.movieapp.BuildConfig
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.services.BaseAPI
import com.kalfian.movieapp.services.alarm.AlarmDailyReceiver
import com.kalfian.movieapp.view.SettingView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

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

        val intent = Intent(context, AlarmDailyReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

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

    override fun setReleaseTodayMovieAlarm(isOn: Boolean, context: Context, data: ResponseMovie.ResultMovie?) {
        TODO("not implemented")
    }

    override fun getReleaseToday(context: Context?) {
        view.showLoader()

        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()).toString()
        compositeDisposable?.add(
            baseApi.movieReleaseToday(BuildConfig.MOVIE_API_KEY, today, today)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribeWith(
                    object : DisposableObserver<Response<ResponseMovie>>(){
                        override fun onComplete() {
                            Log.d("RESPONSE_SEARCH_MOVIE", "Complete")
                            view.hideLoader()
                        }

                        override fun onNext(t: Response<ResponseMovie>) {
                            if(t.code() == 200){
                                view.hideLoader()
                                context?.let { setReleaseTodayMovieAlarm(true, it, t.body()?.results?.get(0)) }
                            } else {
                                view.hideLoader()
                            }
                            view.hideLoader()
                        }

                        override fun onError(e: Throwable) {
                            Log.d("RESPONSE_MOVIE", "Error data :"+e.localizedMessage)
                            view.hideLoader()
                        }
                    }
                )
        )
    }

}