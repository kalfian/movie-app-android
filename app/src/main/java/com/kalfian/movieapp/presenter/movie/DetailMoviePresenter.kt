package com.kalfian.movieapp.presenter.movie

import android.content.Context
import android.net.Uri
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.services.DBAccess
import com.kalfian.movieapp.services.StaticData
import com.kalfian.movieapp.view.DetailView

class DetailMoviePresenter (val view: DetailView.ViewMovie): DetailView.PresenterMovie {
    private lateinit var dataGlobal: ResponseMovie.ResultMovie
    private var idMovie: Long = 0

    private val CONTENT_URI = Uri.Builder()
        .scheme(StaticData.SCHEME)
        .authority(StaticData.AUTHOR)
        .appendPath(ResponseMovie.ResultMovie::class.java.simpleName as String)
        .build()

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

    override fun setFavorite(context: Context) {
        DBAccess.getInstance(context).movieDao().insertMovie(dataGlobal)
        context.contentResolver.insert(CONTENT_URI, dataGlobal.values())
    }

    override fun unsetFavorite(context: Context) {
        DBAccess.getInstance(context).movieDao().removeSpecific(idMovie)
    }

    override fun getFavorite(context: Context): Boolean {
        return DBAccess.getInstance(context).movieDao().getSelectMovie(idMovie) != null
    }
}