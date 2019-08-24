package com.kalfian.movieapp.presenter.movie

import android.content.Context
import android.content.Intent
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.services.DBAccess
import com.kalfian.movieapp.view.MainView
import com.kalfian.movieapp.view.ui.movie.DetailMovieActivity

class FavoriteMoviePresenter(val view: MainView.MovieView, val context: Context?): MainView.FavoriteMoviePresenter {

    private var data: ArrayList<ResponseMovie.ResultMovie>? = null

    override fun getFavoriteMovie(context: Context) {
        view.showLoader()
        val getData = DBAccess.getInstance(context).movieDao().getAllMovie()
        if (getData != null && getData.isNotEmpty()) {
            view.showData(getData as ArrayList<ResponseMovie.ResultMovie>)
            this.data = getData
        } else {
            view.hideLoader()
        }

    }

    override fun toDetail(context: Context, position: Int) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra("Data", data?.get(position))
        context.startActivity(intent)
    }
}