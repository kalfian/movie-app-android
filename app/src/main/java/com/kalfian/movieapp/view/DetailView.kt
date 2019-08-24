package com.kalfian.movieapp.view

import android.content.Context
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.model.ResponseTvShow

interface DetailView {
    interface ViewMovie {
        fun getData()
        fun showData(
            image : String,
            title : String,
            releaseDate : String,
            rating : String,
            popularity : String,
            description : String
        )
        fun checkFavorite()
        fun addFavorite()
        fun removeFavorite()
        fun getFavorite()
    }

    interface PresenterMovie {
        fun extractData(
            context: Context,
            data: ResponseMovie.ResultMovie
        )

        fun setFavorite(
            context: Context
        )
        fun unsetFavorite(
            context: Context
        )
        fun getFavorite(
            context: Context
        ) : Boolean
    }

    interface ViewTVShow {
        fun getData()
        fun showData(
            image : String,
            title : String,
            firstAir : String,
            rating : String,
            popularity : String,
            description : String
        )
        fun checkFavorite()
        fun addFavorite()
        fun removeFavorite()
        fun getFavorite()
    }

    interface PresenterTVShow {
        fun extractData(
            context: Context,
            data: ResponseTvShow.ResultTvShow
        )

        fun setFavorite(
            context: Context
        )
        fun unsetFavorite(
            context: Context
        )
        fun getFavorite(
            context: Context
        ) : Boolean
    }
}