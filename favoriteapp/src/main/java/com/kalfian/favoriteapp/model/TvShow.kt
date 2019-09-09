package com.kalfian.favoriteapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.database.Cursor
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class StringResponseTvShow{
    companion object{
        const val id = "id"
        const val title = "original_name"
        const val poster_path = "poster_path"
        const val overview = "overview"
        const val first_air = "first_air_date"
        const val popularity = "popularity"
        const val vote = "vote_average"
    }
}

class ResponseTvShow {
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
    var results: List<ResultTvShow>? = null

    @Entity(tableName = "tv_show_db")
    @Parcelize
    data class ResultTvShow(
        @PrimaryKey
        @SerializedName("id")
        @Expose
        var id: Long = 0,

        @SerializedName("original_name")
        @Expose
        var title: String = "",

        @SerializedName("poster_path")
        @Expose
        var poster_path: String = "",

        @SerializedName("overview")
        @Expose
        var overview: String = "",

        @SerializedName("first_air_date")
        @Expose
        var first_air: String = "",

        @SerializedName("popularity")
        @Expose
        var popularity: Double = 0.0,

        @SerializedName("vote_average")
        @Expose
        var vote: Double = 0.0
    ) : Parcelable {
        constructor(cursor: Cursor): this() {
            id = cursor.getLong(cursor.getColumnIndex(StringResponseTvShow.id))
            title = cursor.getString(cursor.getColumnIndex(StringResponseTvShow.title))
            poster_path = cursor.getString(cursor.getColumnIndex(StringResponseTvShow.poster_path))
            overview = cursor.getString(cursor.getColumnIndex(StringResponseTvShow.overview))
            first_air = cursor.getString(cursor.getColumnIndex(StringResponseTvShow.first_air))
            popularity = cursor.getDouble(cursor.getColumnIndex(StringResponseTvShow.popularity))
            vote = cursor.getDouble(cursor.getColumnIndex(StringResponseTvShow.vote))
        }
    }
}