package com.kalfian.favoriteapp.presenter.movie

import android.content.Context
import com.kalfian.favoriteapp.model.ResponseMovie
import com.kalfian.favoriteapp.view.DetailView

class DetailMoviePresenter(val view: DetailView.ViewMovie): DetailView.PresenterMovie {
    private lateinit var dataGlobal: ResponseMovie.ResultMovie
    private var idMovie: Long = 0

    override fun extractData(context: Context, data: ResponseMovie.ResultMovie) {
        val image = data.poster_path
        val title = data.title
        val release = data.release_date
        val rating = data.vote_average.toString()
        val popularity  = data.popularity.toString()
        val description = data.overview

        view.showData(image,title,release,rating,popularity,description)

        this.dataGlobal = data
        this.idMovie = data.id
    }

}