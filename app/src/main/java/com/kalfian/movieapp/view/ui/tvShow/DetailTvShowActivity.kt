package com.kalfian.movieapp.view.ui.tvShow

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.kalfian.movieapp.BuildConfig
import com.kalfian.movieapp.R
import com.kalfian.movieapp.presenter.tvShow.DetailTvShowPresenter
import com.kalfian.movieapp.view.DetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity(), DetailView.ViewTVShow {
    private lateinit var presenter: DetailView.PresenterTVShow
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        actionBar?.setDisplayShowHomeEnabled(true)

        presenter = DetailTvShowPresenter(this)
        getData()
        getFavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fav, menu)
        menuItem = menu
        checkFavorite()
        return true
    }

    override fun checkFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_white_24dp)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_white_24dp)
        }
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.home) {
            finish()
        } else if (id == R.id.set_favorite) {
            if (isFavorite) {
                removeFavorite()
            } else {
                addFavorite()
            }
            isFavorite = !isFavorite
            checkFavorite()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun addFavorite() {
        presenter.setFavorite(applicationContext)
    }

    override fun removeFavorite() {
        presenter.unsetFavorite(applicationContext)
    }

    override fun getFavorite() {
        if (presenter.getFavorite(applicationContext)) {
            isFavorite = true
        }
    }
}
