package com.kalfian.movieapp.favoriteapp.view.tvShow


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.kalfian.movieapp.favoriteapp.R
import com.kalfian.movieapp.favoriteapp.model.ResponseTvShow
import com.kalfian.movieapp.favoriteapp.presenter.tvShow.TvShowPresenter
import com.kalfian.movieapp.favoriteapp.view.MainView
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_tv_show.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TvShowFragment : Fragment(), MainView.TvShowView, TvShowAdapter.OnItemClickListener {

    private lateinit var adapter: TvShowAdapter
    private lateinit var presenter: TvShowPresenter
    private lateinit var dataGlobal: ArrayList<ResponseTvShow.ResultTvShow>
    var dialog: AlertDialog? = null

    private val KEYTVSHOW = "DataTvShow"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerView_tv_show.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        dialog = SpotsDialog.Builder().setContext(context).build()

        if (savedInstanceState != null) {
            showData(savedInstanceState.getParcelableArrayList(KEYTVSHOW))
        } else {
            presenter = TvShowPresenter(this)
            getData()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEYTVSHOW, dataGlobal)
    }

    override fun showData(data: ArrayList<ResponseTvShow.ResultTvShow>) {
        adapter = TvShowAdapter(data)
        view?.recyclerView_tv_show?.adapter = adapter
        adapter.setOnItemClickListener(this)

        this.dataGlobal = data
    }

    override fun getData() {
        context?.let { presenter.getTvShow(it) }
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
