package com.kalfian.favoriteapp.presenter.tvShow

import android.content.ContentProviderClient
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.RemoteException
import android.util.Log
import com.kalfian.favoriteapp.model.ResponseTvShow
import com.kalfian.favoriteapp.model.StringResponseTvShow
import com.kalfian.favoriteapp.services.StaticData
import com.kalfian.favoriteapp.view.MainView
import com.kalfian.favoriteapp.view.tvShow.DetailTvShowActivity

class TvShowPresenter (val view: MainView.TvShowView): MainView.TvShowPresenter {

    private var result: ArrayList<ResponseTvShow.ResultTvShow>? = null
    private val TV_SHOW_TABLE = ResponseTvShow.ResultTvShow::class.java.simpleName as String
    private val CONTENT_URI = Uri.parse(StaticData.SCHEME+"://" + StaticData.AUTHOR + "/" + TV_SHOW_TABLE )

    override fun getTvShow(context: Context) {
        Log.d("CONTENT_URI", CONTENT_URI.toString())
        val clientContentProvider: ContentProviderClient = context.contentResolver.acquireContentProviderClient(CONTENT_URI)
        try {
            assert(clientContentProvider != null)
            val cursor = clientContentProvider.query(CONTENT_URI, arrayOf(
                StringResponseTvShow.id,
                StringResponseTvShow.original_name,
                StringResponseTvShow.poster_path,
                StringResponseTvShow.overview,
                StringResponseTvShow.first_air_date,
                StringResponseTvShow.popularity,
                StringResponseTvShow.vote_average
            ), null, null, null, null)

            assert(cursor != null)
            if (cursor.count > 0) {
                view.showData(convertCursor(cursor))
                Log.d("Error","CURSOR ADA")
            } else {
                Log.d("Error","CURSOR KOSONG")
                view.empty()
                cursor.close()
            }
        } catch (e: RemoteException) {
            Log.d("ERROR_CURSOR", e.toString())
        }
    }

    override fun convertCursor(cursor: Cursor): ArrayList<ResponseTvShow.ResultTvShow> {
        val items: ArrayList<ResponseTvShow.ResultTvShow> = ArrayList()

        try {
            while (cursor.moveToNext()) {
                val item = ResponseTvShow.ResultTvShow(cursor)
                Log.d("DATA_ITEM_CURSOR",item.toString())
                items.add(item)
            }

        } catch (e: Error) {
            Log.d("ERROR_CONVERT", e.toString())
        }

        result = items

        return items
    }

    override fun toDetail(context: Context, position: Int) {
        val intent = Intent(context, DetailTvShowActivity::class.java)
        intent.putExtra("Data", result?.get(position))
        context.startActivity(intent)
    }

}