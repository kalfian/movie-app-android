package com.kalfian.movieapp.presenter.movie

import android.content.Context
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.services.DBAccess
import com.kalfian.movieapp.view.DetailView

class DetailMoviePresenter (val view: DetailView.ViewMovie): DetailView.PresenterMovie {
    private lateinit var dataGlobal: ResponseMovie.ResultMovie
    private var idMovie: Long = 0

    override fun extractData(context: Context, data: ResponseMovie.ResultMovie) {
        val image = data.poster_path
        val title = data.title
        val release = data.release_date
        val rating = data.vote.toString()
        val popularity  = data.popularity.toString()
        val description = data.overview

        view.showData(image,title,release,rating,popularity,description)

        this.dataGlobal = data
        this.idMovie = data.id
    }

    override fun setFavorite(context: Context) {
        DBAccess.getInstance(context).movieDao().insertMovie(dataGlobal)
    }

    override fun unsetFavorite(context: Context) {
        DBAccess.getInstance(context).movieDao().removeSpecific(idMovie)
    }

    override fun getFavorite(context: Context): Boolean {
        return DBAccess.getInstance(context).movieDao().getSelectMovie(idMovie) != null
    }
}