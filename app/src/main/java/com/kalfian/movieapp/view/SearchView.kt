package com.kalfian.movieapp.view

import android.content.Context
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.model.ResponseTvShow

interface SearchView {
    interface SearchMovieView {
        fun getData(query: String)
        fun showLoader()
        fun hideLoader()
        fun showData(data: ArrayList<ResponseMovie.ResultMovie>)
    }

    interface SearchMoviePresenter {
        fun getData(query: String)
        fun toDetail(context: Context, position: Int)
    }

    interface SearchTvShowView {
        fun getData(query: String)
        fun showLoader()
        fun hideLoader()
        fun showData(data: ArrayList<ResponseTvShow.ResultTvShow>)
    }

    interface SearchTvShowPresenter {
        fun getData(query: String)
        fun toDetail(context: Context, position: Int)
    }
}