package com.kalfian.favoriteapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.database.Cursor
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class StringResponseMovie {
    companion object{
        const val id = "id"
        const val title = "title"
        const val poster_path = "poster_path"
        const val overview = "overview"
        const val release_date = "release_date"
        const val popularity = "popularity"
        const val vote_average = "vote_average"
    }
}

class ResponseMovie {
    @SerializedName("page")
    @Expose
    var page: Int? = 0

    @SerializedName("total_results")
    @Expose
    var total_results: Long? = 0

    @SerializedName("total_pages")
    @Expose
    var total_pages: Long? = 0

    @SerializedName("results")
    @Expose
    var results: List<ResultMovie>? = null

    @Entity(tableName = "movie_db")
    @Parcelize
    data class ResultMovie (
        @PrimaryKey
        @SerializedName(StringResponseMovie.id)
        @Expose
        var id: Long = 0,

        @SerializedName(StringResponseMovie.title)
        @Expose
        var title: String = "",

        @SerializedName(StringResponseMovie.poster_path)
        @Expose
        var poster_path: String = "",

        @SerializedName(StringResponseMovie.overview)
        @Expose
        var overview: String = "",

        @SerializedName(StringResponseMovie.release_date)
        @Expose
        var release_date: String = "",

        @SerializedName(StringResponseMovie.popularity)
        @Expose
        var popularity: Double = 0.0,

        @SerializedName(StringResponseMovie.vote_average)
        @Expose
        var vote_average: Double= 0.0

    ) : Parcelable {
        constructor(cursor: Cursor): this() {
            id = (cursor.getLong(cursor.getColumnIndex(StringResponseMovie.id)))
            title = (cursor.getString(cursor.getColumnIndex(StringResponseMovie.title)))
            poster_path = (cursor.getString(cursor.getColumnIndex(StringResponseMovie.poster_path)))
            overview = (cursor.getString(cursor.getColumnIndex(StringResponseMovie.overview)))
            release_date = (cursor.getString(cursor.getColumnIndex(StringResponseMovie.release_date)))
            popularity = (cursor.getDouble(cursor.getColumnIndex(StringResponseMovie.popularity)))
            vote_average = (cursor.getDouble(cursor.getColumnIndex(StringResponseMovie.vote_average)))

        }
    }
}