package com.kalfian.movieapp.activity

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Movie
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.kalfian.movieapp.R
import com.kalfian.movieapp.view.ui.favorite.FavoriteFragment
import com.kalfian.movieapp.view.ui.movie.MovieFragment
import com.kalfian.movieapp.view.ui.movie.SearchMovieActivity
import com.kalfian.movieapp.view.ui.tvShow.SearchTvShowActivity
import com.kalfian.movieapp.view.ui.tvShow.TVShowFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var menuItem: Menu? = null
    private var isMovie = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val drawerNavView: NavigationView = findViewById(R.id.nav_view_drawer)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        drawerNavView.setNavigationItemSelectedListener(this)

        moveFragment(MovieFragment(), getString(R.string.bottom_movie))
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        if (menu != null) {
            val searchItem: MenuItem = menu.findItem(R.id.search_tab)
            val searchView: SearchView = searchItem.actionView as SearchView
            menuItem = menu
            searchData(searchView)
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun searchData(searchView: SearchView) {
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("query", query)

                menuItem?.getItem(0)?.collapseActionView()

                searchView.clearFocus()
                if (isMovie) {
                    val data = Intent(applicationContext, SearchMovieActivity::class.java)
                    data.putExtra("query", query)
                    startActivity(data)
                } else {
                    val data = Intent(applicationContext, SearchTvShowActivity::class.java)
                    data.putExtra("query", query)
                    startActivity(data)
                }

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.drawer_navigation_movie -> {
                moveFragment(MovieFragment(), getString(R.string.bottom_movie))
            }
            R.id.drawer_navigation_tv_show -> {
                moveFragment(TVShowFragment(), getString(R.string.bottom_tv))
            }
            R.id.drawer_navigation_favorite -> {
                moveFragment(FavoriteFragment(), getString(R.string.bottom_favorite))
            }
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if(newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE){

        } else if(newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }

    private fun moveFragment(fragment: Fragment, title: String) {
        supportActionBar?.title = title

        menuItem?.getItem(0)?.isVisible = true
        when (title) {
            "Movie" -> isMovie = true
            "TV Show" -> isMovie = false
            else -> menuItem?.getItem(0)?.isVisible = false
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, fragment)
            .commit()
    }
}
