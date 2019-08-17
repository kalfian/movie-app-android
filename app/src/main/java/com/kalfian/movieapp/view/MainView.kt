package com.kalfian.movieapp.view

import android.content.Context
import com.kalfian.movieapp.model.ResponseMovie

interface MainView {
    interface MovieView {
        fun showData(data: ArrayList<ResponseMovie.ResultMovie>)
        fun getData()
    }

    interface MoviePresenter {
        fun getMovie()
        fun goToDetailMovie(context: Context, position: Int)
    }
}