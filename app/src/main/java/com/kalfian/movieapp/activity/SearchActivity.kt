package com.kalfian.movieapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.kalfian.movieapp.R
import com.kalfian.movieapp.view.MainView


class SearchActivity : AppCompatActivity(), MainView.SearchView {

    private var query = ""
    private var isMovie = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.title = getString(R.string.Search)

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
        }
        return true
    }

    override fun getData(query: String, isMovie: Boolean) {

    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun showData(data: ArrayList<Any>) {

    }
}
