package com.kalfian.favoriteapp.presenter.movie

import android.content.ContentProviderClient
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.RemoteException
import android.util.Log
import com.kalfian.favoriteapp.model.ResponseMovie
import com.kalfian.favoriteapp.model.StringResponseMovie
import com.kalfian.favoriteapp.services.StaticData
import com.kalfian.favoriteapp.view.MainView
import com.kalfian.favoriteapp.view.movie.DetailMovieActivity

class MoviePresenter(val view: MainView.MovieView): MainView.MoviePresenter {


    private var result : ArrayList<ResponseMovie.ResultMovie>? = null
    private val MOVIE_TABLE = ResponseMovie.ResultMovie::class.java.simpleName as String
    private val CONTENT_URI = Uri.parse(StaticData.SCHEME+"://" + StaticData.AUTHOR + "/" + MOVIE_TABLE )

    override fun getMovie(context: Context) {
        Log.d("CONTENT_URI", CONTENT_URI.toString())
        val clientContentProvider: ContentProviderClient = context.contentResolver.acquireContentProviderClient(CONTENT_URI)
        try {
            assert(clientContentProvider != null)
            val cursor = clientContentProvider.query(CONTENT_URI, arrayOf(
                StringResponseMovie.id,
                StringResponseMovie.title,
                StringResponseMovie.poster_path,
                StringResponseMovie.overview,
                StringResponseMovie.release_date,
                StringResponseMovie.popularity,
                StringResponseMovie.vote_average
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

    override fun convertCursor(cursor: Cursor): ArrayList<ResponseMovie.ResultMovie> {
        val items: ArrayList<ResponseMovie.ResultMovie> = ArrayList()

        try {
            while (cursor.moveToNext()) {
                val item = ResponseMovie.ResultMovie(cursor)
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
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra("Data", result?.get(position))
        context.startActivity(intent)
    }

}