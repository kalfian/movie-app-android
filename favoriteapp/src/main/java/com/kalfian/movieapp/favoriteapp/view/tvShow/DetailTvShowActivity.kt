package com.kalfian.movieapp.favoriteapp.view.tvShow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.kalfian.movieapp.favoriteapp.BuildConfig
import com.kalfian.movieapp.favoriteapp.R
import com.kalfian.movieapp.favoriteapp.presenter.tvShow.DetailTvShowPresenter
import com.kalfian.movieapp.favoriteapp.view.DetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity(), DetailView.ViewTVShow {
    private lateinit var presenter: DetailView.PresenterTVShow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        actionBar?.setDisplayShowHomeEnabled(true)

        presenter = DetailTvShowPresenter(this)
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
        firstAir: String,
        rating: String,
        popularity: String,
        description: String
    ) {
        Picasso.get().load(BuildConfig.MOVIE_PATH + image).into(thumbnail)

        tv_show_name.text = title
        tv_show_first_air.text = firstAir
        tv_show_vote.text = rating
        tv_show_popularity.text = popularity
        tv_show_overview.text = description

        supportActionBar?.title = tv_show_name.text
    }
}
