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
        const val original_name = "original_name"
        const val poster_path = "poster_path"
        const val overview = "overview"
        const val first_air_date = "first_air_date"
        const val popularity = "popularity"
        const val vote_average = "vote_average"
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
        var original_name: String = "",

        @SerializedName("poster_path")
        @Expose
        var poster_path: String = "",

        @SerializedName("overview")
        @Expose
        var overview: String = "",

        @SerializedName("first_air_date")
        @Expose
        var first_air_date: String = "",

        @SerializedName("popularity")
        @Expose
        var popularity: Double = 0.0,

        @SerializedName("vote_average")
        @Expose
        var vote_average: Double = 0.0
    ) : Parcelable {
        constructor(cursor: Cursor): this() {
            id = cursor.getLong(cursor.getColumnIndex(StringResponseTvShow.id))
            original_name = cursor.getString(cursor.getColumnIndex(StringResponseTvShow.original_name))
            poster_path = cursor.getString(cursor.getColumnIndex(StringResponseTvShow.poster_path))
            overview = cursor.getString(cursor.getColumnIndex(StringResponseTvShow.overview))
            first_air_date = cursor.getString(cursor.getColumnIndex(StringResponseTvShow.first_air_date))
            popularity = cursor.getDouble(cursor.getColumnIndex(StringResponseTvShow.popularity))
            vote_average = cursor.getDouble(cursor.getColumnIndex(StringResponseTvShow.vote_average))
        }
    }
}