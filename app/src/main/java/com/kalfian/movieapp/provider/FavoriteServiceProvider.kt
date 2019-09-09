package com.kalfian.movieapp.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.model.ResponseTvShow
import com.kalfian.movieapp.services.DBAccess
import com.kalfian.movieapp.services.StaticData
import java.util.*

class FavoriteServiceProvider : ContentProvider() {

    private val AUTHOR = StaticData.AUTHOR
    private val MOVIE = 1
    private val TV_SHOW = 2
    private val MOVIE_TABLE = ResponseMovie.ResultMovie::class.java.simpleName as String
    private val TV_SHOW_TABLE = ResponseTvShow.ResultTvShow::class.java.simpleName as String

    private val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(AUTHOR, MOVIE_TABLE, MOVIE)
        uriMatcher.addURI(AUTHOR, TV_SHOW_TABLE, TV_SHOW)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Objects.requireNonNull(context).contentResolver.notifyChange(uri, null)
        return uri
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            MOVIE -> {
                val movieDao = DBAccess.getInstance(context).movieDao()
                movieDao.getAllMovieCursor()
            }
            TV_SHOW -> {
                val tvShowDao = DBAccess.getInstance(context).tvshowDao()
                tvShowDao.getAllTvShowCursor()
            }
            else -> null
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        Objects.requireNonNull(context).contentResolver.notifyChange(uri, null)
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

}