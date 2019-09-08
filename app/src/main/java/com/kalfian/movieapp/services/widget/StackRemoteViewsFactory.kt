package com.kalfian.movieapp.services.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.kalfian.movieapp.BuildConfig
import com.kalfian.movieapp.R
import com.kalfian.movieapp.services.DBAccess
import java.net.URL

class StackRemoteViewsFactory(val context: Context): RemoteViewsService.RemoteViewsFactory {

    private var images: ArrayList<Bitmap> = ArrayList()

    override fun onCreate() {

    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun onDataSetChanged() {
        images.clear()
        val getMovies = DBAccess.getInstance(context).movieDao().getAllMovie()
        val getTvShows = DBAccess.getInstance(context).tvshowDao().getAllTvshow()

        for (data in getMovies) {
            val url = URL(BuildConfig.MOVIE_PATH + data.poster_path)
            images.add(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }

        for (data in getTvShows) {
            val url = URL(BuildConfig.MOVIE_PATH + data.poster_path)
            images.add(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }


    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getViewAt(position: Int): RemoteViews {
        val view = RemoteViews(context.packageName, R.layout.item_widget)
        view.setImageViewBitmap(R.id.imageView_widget_favorite, images[position])

        return view
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun onDestroy() {

    }

}