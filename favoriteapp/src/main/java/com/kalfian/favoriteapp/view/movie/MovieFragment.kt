package com.kalfian.favoriteapp.view.movie


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.kalfian.favoriteapp.R
import com.kalfian.favoriteapp.model.ResponseMovie
import com.kalfian.favoriteapp.presenter.movie.MoviePresenter
import com.kalfian.favoriteapp.view.MainView
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_movie.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MovieFragment : Fragment(), MainView.MovieView, MovieAdapter.OnItemClickListener {

    private lateinit var adapter: MovieAdapter
    private lateinit var presenter : MoviePresenter
    private lateinit var dataGlobal: ArrayList<ResponseMovie.ResultMovie>

    private val KEYMOVIE = "DataMovie"

    var dialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerView_movie.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        dialog = SpotsDialog.Builder().setContext(context).build()

        if (savedInstanceState != null && savedInstanceState.containsKey(KEYMOVIE)) {
            showData(savedInstanceState.getParcelableArrayList(KEYMOVIE))
        } else {
            presenter = MoviePresenter(this)
            getData()
        }

        view.swipe_movie.setOnRefreshListener {
            presenter = MoviePresenter(this)
            context?.let { presenter.getMovie(it) }
            view.swipe_movie.isRefreshing = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(::dataGlobal.isInitialized) {
            outState.putParcelableArrayList(KEYMOVIE, dataGlobal)
        }
    }

    override fun showData(data: ArrayList<ResponseMovie.ResultMovie>) {
        adapter = MovieAdapter(data)
        view?.recyclerView_movie?.adapter = adapter
        adapter.setOnItemClickListener(this)

        this.dataGlobal = data
    }

    override fun getData() {
        context?.let { presenter.getMovie(it) }
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

    override fun empty() {

    }

}
