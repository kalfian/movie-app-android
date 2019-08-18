package com.kalfian.movieapp.view

import android.content.Context
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.model.ResponseTvShow

interface MainView {
    interface MovieView {
        fun showData(data: ArrayList<ResponseMovie.ResultMovie>)
        fun getData()
    }

    interface MoviePresenter {
        fun getMovie()
        fun goToDetailMovie(context: Context, position: Int)
    }

    interface TvShowView {
        fun showData(data: ArrayList<ResponseTvShow.ResultTvShow>)
        fun getData()
    }

    interface TvShowPresenter {
        fun getTvShow()
        fun goToDetailTvShow(context: Context, position: Int)
    }
}