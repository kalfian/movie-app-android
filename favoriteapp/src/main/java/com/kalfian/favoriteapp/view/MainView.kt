package com.kalfian.favoriteapp.view

import android.content.Context
import android.database.Cursor
import com.kalfian.favoriteapp.model.ResponseMovie
import com.kalfian.favoriteapp.model.ResponseTvShow

interface MainView {
    interface MovieView {
        fun showData(data: ArrayList<ResponseMovie.ResultMovie>)
        fun getData()
        fun empty()
        fun showLoader()
        fun hideLoader()
    }

    interface MoviePresenter {
        fun getMovie(context: Context)
        fun toDetail(context: Context, position: Int)
        fun convertCursor(cursor: Cursor): ArrayList<ResponseMovie.ResultMovie>
    }

    interface TvShowView {
        fun showData(data: ArrayList<ResponseTvShow.ResultTvShow>)
        fun getData()
        fun empty()
        fun showLoader()
        fun hideLoader()
    }

    interface TvShowPresenter {
        fun getTvShow(context: Context)
        fun toDetail(context: Context, position: Int)
        fun convertCursor(cursor: Cursor): ArrayList<ResponseTvShow.ResultTvShow>
    }

}