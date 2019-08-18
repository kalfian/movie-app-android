package com.kalfian.movieapp.view.ui.tvShow


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
import com.kalfian.movieapp.presenter.TvShowPresenter
import com.kalfian.movieapp.view.MainView
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_tvshow.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TVShowFragment : Fragment(), MainView.TvShowView, TvShowAdapter.OnItemClickListener {

    private lateinit var adapter: TvShowAdapter
    private lateinit var presenter: TvShowPresenter
    private lateinit var dataGlobal: ArrayList<ResponseTvShow.ResultTvShow>

    private val KEYTVSHOW = "DataTvShow"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerView_tv_show.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

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
        presenter.getTvShow()
    }

    override fun onItemClickListener(position: Int) {
        context?.let { presenter.goToDetailTvShow(it, position) }
    }
}
