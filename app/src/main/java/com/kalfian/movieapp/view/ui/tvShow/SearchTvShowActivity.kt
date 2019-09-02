package com.kalfian.movieapp.view.ui.tvShow

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.kalfian.movieapp.R
import com.kalfian.movieapp.model.ResponseTvShow
import com.kalfian.movieapp.presenter.search.SearchTvShowPresenter
import com.kalfian.movieapp.view.SearchView
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_search_tv_show.*

class SearchTvShowActivity : AppCompatActivity(), SearchView.SearchTvShowView, TvShowAdapter.OnItemClickListener {

    private lateinit var adapter: TvShowAdapter
    private lateinit var presenter : SearchTvShowPresenter
    private lateinit var dataGlobal: ArrayList<ResponseTvShow.ResultTvShow>

    private val KEYTVSHOW = "DataSearchTvShow"

    var dialog: AlertDialog? = null
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_tv_show)

        dialog = SpotsDialog.Builder().setContext(this).build()
        query = intent.getStringExtra("query")
        supportActionBar?.title = getString(R.string.Search)
        recyclerView_tv_show.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        if (savedInstanceState != null) {
            showData(savedInstanceState.getParcelableArrayList(KEYTVSHOW))
        } else {
            presenter = SearchTvShowPresenter(this)
            getData(query)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEYTVSHOW, dataGlobal)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        if (menu != null) {
            val searchItem: MenuItem = menu.findItem(R.id.search_tab)
            val searchView: android.support.v7.widget.SearchView = searchItem.actionView as android.support.v7.widget.SearchView
            searchItem.expandActionView()
            searchView.clearFocus()
            searchView.setQuery(query, false)

            searchItem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    finish()

                    return true
                }

            })

            searchData(searchView)
        }
        return true
    }

    private fun searchData(searchView: android.support.v7.widget.SearchView) {
        searchView.setOnQueryTextListener(object: android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                Log.d("query", q)

                searchView.setQuery(query, false)
                searchView.clearFocus()
                val data = Intent(applicationContext, SearchTvShowActivity::class.java)
                data.putExtra("query", q)
                startActivity(data)

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    override fun getData(query: String) {
        presenter.getData(query)
    }

    override fun showLoader() {
        dialog?.show()
    }

    override fun hideLoader() {
        dialog?.dismiss()
    }

    override fun showData(data: ArrayList<ResponseTvShow.ResultTvShow>) {
        adapter = TvShowAdapter(data)
        recyclerView_tv_show.adapter = adapter
        adapter.setOnItemClickListener(this)

        this.dataGlobal = data
    }

    override fun onItemClickListener(position: Int) {
        presenter.toDetail(this, position)
    }
}
