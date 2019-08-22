package com.kalfian.movieapp.presenter.tvShow

import android.content.Context
import com.kalfian.movieapp.model.ResponseTvShow
import com.kalfian.movieapp.view.DetailView

class DetailTvShowPresenter(val view: DetailView.ViewTVShow): DetailView.PresenterTVShow {

    private lateinit var dataGlobal: ResponseTvShow.ResultTvShow
    private var idMovie: Long = 0

    override fun extractData(context: Context, data: ResponseTvShow.ResultTvShow) {
        val image = data.poster_path
        val title = data.title
        val firstAir = data.first_air
        val rating = data.vote.toString()
        val popularity  = data.popularity.toString()
        val description = data.overview

        view.showData(image,title,firstAir,rating,popularity,description)

        this.dataGlobal = data
        this.idMovie = data.id
    }

}