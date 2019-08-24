package com.kalfian.movieapp.view.ui.favorite

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kalfian.movieapp.R

class FavoriteAdapter(fm : FragmentManager, val context : Context) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return when(p0){
            0 -> FavoriteMovieFragment()
            else -> FavoriteTvShowFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return context.resources.getString(R.string.bottom_movie)
            else -> return context.resources.getString(R.string.bottom_tv)
        }
    }
}