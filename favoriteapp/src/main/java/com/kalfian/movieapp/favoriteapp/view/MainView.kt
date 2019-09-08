package com.kalfian.movieapp.favoriteapp.view

import android.content.Context
import com.kalfian.movieapp.favoriteapp.model.ResponseMovie
import com.kalfian.movieapp.favoriteapp.model.ResponseTvShow

interface MainView {
    interface MovieView {
        fun showData(data: ArrayList<ResponseMovie.ResultMovie>)
        fun getData()
        fun showLoader()
        fun hideLoader()
    }

    interface MoviePresenter {
        fun getMovie(context: Context)
        fun toDetail(context: Context, position: Int)
    }

    interface TvShowView {
        fun showData(data: ArrayList<ResponseTvShow.ResultTvShow>)
        fun getData()
        fun showLoader()
        fun hideLoader()
    }

    interface TvShowPresenter {
        fun getTvShow(context: Context)
        fun toDetail(context: Context, position: Int)
    }

}