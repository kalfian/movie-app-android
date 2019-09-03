package com.kalfian.movieapp.view

import android.content.Context
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.model.ResponseTvShow

interface MainView {
    interface MovieView {
        fun showData(data: ArrayList<ResponseMovie.ResultMovie>)
        fun getData()
        fun showLoader()
        fun hideLoader()
    }

    interface MoviePresenter {
        fun getMovie()
        fun goToDetailMovie(context: Context, position: Int)
    }

    interface FavoriteMoviePresenter {
        fun getFavoriteMovie(context: Context)
        fun toDetail(context: Context, position: Int)
    }

    interface TvShowView {
        fun showData(data: ArrayList<ResponseTvShow.ResultTvShow>)
        fun getData()
        fun showLoader()
        fun hideLoader()
    }

    interface TvShowPresenter {
        fun getTvShow()
        fun goToDetailTvShow(context: Context, position: Int)
    }

    interface FavoritTvShowPresenter {
        fun getFavoriteTvShow(context: Context)
        fun toDetail(context: Context, position: Int)
    }

    interface SearchView {
        fun getData(query: String, isMovie: Boolean)
        fun showLoader()
        fun hideLoader()
        fun showData(data: ArrayList<Any>)
    }

    interface SearchPresenter {
        fun getSearch(context: Context, isMovie: Boolean, query: String)
        fun toDetail(context: Context, position: Int, isMovie: Boolean)
    }

    interface SettingView {
        fun switchLang()
    }

    interface SettingPresenter {

    }
}