package com.kalfian.favoriteapp.presenter.tvShow

import android.content.Context
import android.content.Intent
import android.database.Cursor
import com.kalfian.favoriteapp.model.ResponseTvShow
import com.kalfian.favoriteapp.view.MainView
import com.kalfian.favoriteapp.view.tvShow.DetailTvShowActivity

class TvShowPresenter (val view: MainView.TvShowView): MainView.TvShowPresenter {

    private var result: ArrayList<ResponseTvShow.ResultTvShow>? = null

    override fun getTvShow(context: Context) {
        val tvShows = ArrayList<ResponseTvShow.ResultTvShow>()

        val tvShow = ResponseTvShow.ResultTvShow()
        tvShow.id = 1
        tvShow.overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."
        tvShow.popularity = 5.0
        tvShow.poster_path = "/is7DUNsw59EcTwCO1FgECbNIfu0.jpg"
        tvShow.first_air = "2019-09-06"
        tvShow.title = "Desmontando la Historia"
        tvShow.vote = 40.0

        tvShows.add(tvShow)
        tvShows.add(tvShow)
        tvShows.add(tvShow)
        tvShows.add(tvShow)
        tvShows.add(tvShow)

        view.showData(tvShows)
        result = tvShows
    }

    override fun convertCursor(cursor: Cursor): ArrayList<ResponseTvShow.ResultTvShow> {
        return ArrayList()
    }

    override fun toDetail(context: Context, position: Int) {
        val intent = Intent(context, DetailTvShowActivity::class.java)
        intent.putExtra("Data", result?.get(position))
        context.startActivity(intent)
    }

}