package com.kalfian.favoriteapp.view.favorite

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kalfian.favoriteapp.R
import com.kalfian.favoriteapp.view.movie.MovieFragment
import com.kalfian.favoriteapp.view.tvShow.TvShowFragment

class FavoriteAdapter(fm : FragmentManager, val context : Context) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return when(p0){
            0 -> MovieFragment()
            else -> TvShowFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> context.resources.getString(R.string.movie_title)
            else -> context.resources.getString(R.string.tv_show_title)
        }
    }
}