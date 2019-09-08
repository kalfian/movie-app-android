package com.kalfian.movieapp.favoriteapp.view.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.kalfian.movieapp.favoriteapp.R
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {

    private lateinit var adapter : FavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = context?.let { FavoriteAdapter(childFragmentManager, it) }!!
        view.viewPager_favorite.adapter = adapter
        view.tabLayout_favorite.setupWithViewPager(view.viewPager_favorite)
    }


}
