package com.kalfian.movieapp.view.ui.setting


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.kalfian.movieapp.R
import com.kalfian.movieapp.presenter.setting.SettingPresenter
import com.kalfian.movieapp.services.StaticData
import com.kalfian.movieapp.view.SettingView
import com.pixplicity.easyprefs.library.Prefs
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting.view.*

class SettingFragment : Fragment(), SettingView.ViewSetting, View.OnClickListener {

    private lateinit var presenter : SettingPresenter
    var dialog: AlertDialog? = null
    var isDailyAlarmOn = false
    var isReleaseTodayOn = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog = SpotsDialog.Builder().setContext(context).build()
        presenter = SettingPresenter(this)

        isDailyAlarmOn = Prefs.getBoolean(StaticData.IS_DAILY_ALARM_ON, false)
        isReleaseTodayOn = Prefs.getBoolean(StaticData.IS_TODAY_MOVIE_ALARM_ON, false)

        view.daily_reminder_switch.isChecked = isDailyAlarmOn
        view.release_reminder_switch.isChecked = isReleaseTodayOn

        view.daily_reminder_switch.setOnClickListener(this)
        view.release_reminder_switch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.daily_reminder_switch -> setDailyAlarm()
            R.id.release_reminder_switch -> setReleaseMovieToday()
        }
    }

    override fun setDailyAlarm() {
        isDailyAlarmOn = !isDailyAlarmOn
        context?.let { presenter.setDailyAlarm(isDailyAlarmOn, it) }
        Prefs.putBoolean(StaticData.IS_DAILY_ALARM_ON, isDailyAlarmOn)
        view?.daily_reminder_switch?.isChecked = isDailyAlarmOn
    }

    override fun setReleaseMovieToday() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoader() {
        dialog?.show()
    }

    override fun hideLoader() {
        dialog?.dismiss()
    }
}
