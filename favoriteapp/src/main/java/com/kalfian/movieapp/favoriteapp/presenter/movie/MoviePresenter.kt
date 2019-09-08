package com.kalfian.movieapp.favoriteapp.presenter.movie

import android.content.Context
import android.content.Intent
import com.kalfian.movieapp.favoriteapp.model.ResponseMovie
import com.kalfian.movieapp.favoriteapp.view.MainView
import com.kalfian.movieapp.favoriteapp.view.movie.DetailMovieActivity

class MoviePresenter(val view: MainView.MovieView): MainView.MoviePresenter {

    private var result : ArrayList<ResponseMovie.ResultMovie>? = null

    override fun getMovie(context: Context) {
        val movies = ArrayList<ResponseMovie.ResultMovie>()

        // Static
        val movie1 = ResponseMovie.ResultMovie()
        movie1.id = 1
        movie1.overview = "27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call."
        movie1.popularity = 5.0
        movie1.poster_path = "/zfE0R94v1E8cuKAerbskfD3VfUt.jpg"
        movie1.release_date = "2019-09-06"
        movie1.title = "It Chapter Two"
        movie1.vote = 40.0

        movies.add(movie1)
        movies.add(movie1)
        movies.add(movie1)
        movies.add(movie1)

        view.showData(movies)
        result = movies
    }

    override fun toDetail(context: Context, position: Int) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra("Data", result?.get(position))
        context.startActivity(intent)
    }

}