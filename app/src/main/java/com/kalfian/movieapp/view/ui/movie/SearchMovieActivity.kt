package com.kalfian.movieapp.view.ui.movie

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.kalfian.movieapp.R
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.presenter.search.SearchMoviePresenter
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_search_movie.*

class SearchMovieActivity : AppCompatActivity(), com.kalfian.movieapp.view.SearchView.SearchMovieView, MovieAdapter.OnItemClickListener {

    private lateinit var adapter: MovieAdapter
    private lateinit var presenter : SearchMoviePresenter
    private lateinit var dataGlobal: ArrayList<ResponseMovie.ResultMovie>

    private val KEYMOVIE = "DataSearchMovie"

    var dialog: AlertDialog? = null
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        dialog = SpotsDialog.Builder().setContext(this).build()
        query = intent.getStringExtra("query")
        supportActionBar?.title = getString(R.string.Search)
        recyclerView_movie.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        if (savedInstanceState != null) {
            showData(savedInstanceState.getParcelableArrayList(KEYMOVIE))
        } else {
            presenter = SearchMoviePresenter(this)
            getData(query)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEYMOVIE, dataGlobal)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        if (menu != null) {
            val searchItem: MenuItem = menu.findItem(R.id.search_tab)
            val searchView: SearchView = searchItem.actionView as SearchView
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

    private fun searchData(searchView: SearchView) {
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(q: String?): Boolean {
                Log.d("query", q)

                searchView.setQuery(query, false)
                searchView.clearFocus()
                val data = Intent(applicationContext, SearchMovieActivity::class.java)
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

    override fun showData(data: ArrayList<ResponseMovie.ResultMovie>) {
        adapter = MovieAdapter(data)
        recyclerView_movie.adapter = adapter
        adapter.setOnItemClickListener(this)

        this.dataGlobal = data
    }

    override fun onItemClick(pos: Int) {
        presenter.toDetail(this, pos)
    }
}
