package com.kalfian.movieapp.favoriteapp.view.movie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.kalfian.movieapp.favoriteapp.BuildConfig
import com.kalfian.movieapp.favoriteapp.R
import com.kalfian.movieapp.favoriteapp.presenter.movie.DetailMoviePresenter
import com.kalfian.movieapp.favoriteapp.view.DetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity(), DetailView.ViewMovie {

    private lateinit var presenter: DetailView.PresenterMovie
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        actionBar?.setDisplayShowHomeEnabled(true)


        presenter = DetailMoviePresenter(this)
        getData()
    }

    override fun getData() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            presenter.extractData(
                applicationContext,
                bundle.getParcelable("Data")
            )
        }
    }

    override fun showData(
        image: String,
        title: String,
        releaseDate: String,
        rating: String,
        popularity: String,
        description: String
    ) {
        Picasso.get().load(BuildConfig.MOVIE_PATH + image).into(thumbnail)

        movie_name.text = title
        movie_release.text = releaseDate
        movie_vote.text = rating
        movie_popularity.text = popularity
        movie_overview.text = description

        supportActionBar?.title = title
    }
}
