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
    }

    interface PresenterMovie {
        fun extractData(
            context: Context,
            data: ResponseMovie.ResultMovie
        )
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
    }

    interface PresenterTVShow {
        fun extractData(
            context: Context,
            data: ResponseTvShow.ResultTvShow
        )
    }
}