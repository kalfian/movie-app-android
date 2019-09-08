package com.kalfian.movieapp.presenter.tvShow

import android.content.Context
import android.content.Intent
import com.kalfian.movieapp.model.ResponseTvShow
import com.kalfian.movieapp.services.DBAccess
import com.kalfian.movieapp.view.MainView
import com.kalfian.movieapp.view.ui.tvShow.DetailTvShowActivity

class FavoriteTvShowPresenter(val view: MainView.TvShowView, val context: Context?): MainView.FavoriteTvShowPresenter {

    private var data: ArrayList<ResponseTvShow.ResultTvShow>? = null

    override fun getFavoriteTvShow(context: Context) {
        view.showLoader()
        val getData = DBAccess.getInstance(context).tvshowDao().getAllTvshow()
        if (getData != null && getData.isNotEmpty()) {
            view.showData(getData as ArrayList<ResponseTvShow.ResultTvShow>)
            this.data = getData
        } else {
            view.hideLoader()
        }
    }

    override fun toDetail(context: Context, position: Int) {
        val intent = Intent(context, DetailTvShowActivity::class.java)
        intent.putExtra("Data", data?.get(position))
        context.startActivity(intent)
    }
}