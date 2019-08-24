package com.kalfian.movieapp.presenter.tvShow

import android.content.Context
import com.kalfian.movieapp.model.ResponseTvShow
import com.kalfian.movieapp.services.DBAccess
import com.kalfian.movieapp.view.DetailView

class DetailTvShowPresenter(val view: DetailView.ViewTVShow): DetailView.PresenterTVShow {

    private lateinit var dataGlobal: ResponseTvShow.ResultTvShow
    private var idTvShow: Long = 0

    override fun extractData(context: Context, data: ResponseTvShow.ResultTvShow) {
        val image = data.poster_path
        val title = data.title
        val firstAir = data.first_air
        val rating = data.vote.toString()
        val popularity  = data.popularity.toString()
        val description = data.overview

        view.showData(image,title,firstAir,rating,popularity,description)

        this.dataGlobal = data
        this.idTvShow = data.id
    }

    override fun setFavorite(context: Context) {
        DBAccess.getInstance(context).tvshowDao().insertTvshow(dataGlobal)
    }

    override fun unsetFavorite(context: Context) {
        DBAccess.getInstance(context).tvshowDao().removeSpecific(idTvShow)
    }

    override fun getFavorite(context: Context): Boolean {
        return DBAccess.getInstance(context).tvshowDao().getSelectTvshow(idTvShow) != null
    }

}