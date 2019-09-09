package com.kalfian.movieapp.view.ui.favorite


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.kalfian.movieapp.R
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.presenter.movie.FavoriteMoviePresenter
import com.kalfian.movieapp.presenter.movie.MoviePresenter
import com.kalfian.movieapp.view.MainView
import com.kalfian.movieapp.view.ui.movie.MovieAdapter
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_favorite_movie.view.*

class FavoriteMovieFragment : Fragment(), MainView.MovieView, MovieAdapter.OnItemClickListener {

    private lateinit var presenter: FavoriteMoviePresenter
    private lateinit var adapter: MovieAdapter
    private lateinit var dataGlobal: ArrayList<ResponseMovie.ResultMovie>

    private val KEYMOVIE = "DataMovieFavorite"

    var dialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerView_movie_favorite.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        dialog = SpotsDialog.Builder().setContext(context).build()

        if (savedInstanceState != null) {
            showData(savedInstanceState.getParcelableArrayList(KEYMOVIE))
        } else {
            presenter = FavoriteMoviePresenter(this, context)
            getData()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(::dataGlobal.isInitialized) {
            outState.putParcelableArrayList(KEYMOVIE, dataGlobal)
        }
    }

    override fun showData(data: ArrayList<ResponseMovie.ResultMovie>) {
        hideLoader()
        adapter = MovieAdapter(data)
        view?.recyclerView_movie_favorite?.adapter = adapter
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickListener(this)
        this.dataGlobal = data
    }

    override fun getData() {
        context?.let { presenter.getFavoriteMovie(it) }
    }

    override fun showLoader() {
        dialog?.show()
    }

    override fun hideLoader() {
        dialog?.dismiss()
    }

    override fun onItemClick(pos: Int) {
        context?.let { presenter.toDetail(it, pos) }
    }

}
