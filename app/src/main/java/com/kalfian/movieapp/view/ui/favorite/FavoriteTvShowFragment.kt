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
import com.kalfian.movieapp.model.ResponseTvShow
import com.kalfian.movieapp.presenter.tvShow.FavoriteTvShowPresenter
import com.kalfian.movieapp.view.MainView
import com.kalfian.movieapp.view.ui.tvShow.TvShowAdapter
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.view.*

class FavoriteTvShowFragment : Fragment(), MainView.TvShowView, TvShowAdapter.OnItemClickListener {

    private lateinit var presenter: FavoriteTvShowPresenter
    private lateinit var adapter: TvShowAdapter
    private lateinit var dataGlobal: ArrayList<ResponseTvShow.ResultTvShow>

    var dialog: AlertDialog? = null

    private val KEYTVSHOW = "DataMovieFavorite"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerView_tv_show_favorite.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        dialog = SpotsDialog.Builder().setContext(context).build()

        if (savedInstanceState != null) {
            showData(savedInstanceState.getParcelableArrayList(KEYTVSHOW))
        } else {
            presenter = FavoriteTvShowPresenter(this, context)
            getData()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEYTVSHOW, dataGlobal)
    }

    override fun showData(data: ArrayList<ResponseTvShow.ResultTvShow>) {
        hideLoader()
        adapter = TvShowAdapter(data)
        view?.recyclerView_tv_show_favorite?.adapter = adapter
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickListener(this)
        this.dataGlobal = data
    }

    override fun getData() {
        context?.let { presenter.getFavoriteTvShow(it) }
    }

    override fun showLoader() {
        dialog?.show()
    }

    override fun hideLoader() {
        dialog?.dismiss()
    }

    override fun onItemClickListener(position: Int) {
        context?.let { presenter.toDetail(it, position) }
    }
}
