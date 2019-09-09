package com.kalfian.movieapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.ContentValues
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class StringResponseTvShow{
    companion object{
        const val id = "id"
        const val original_name = "original_name"
        const val poster_path = "poster_path"
        const val overview = "overview"
        const val first_air = "first_air_date"
        const val popularity = "popularity"
        const val vote_average = "vote_average"
    }
}

class ResponseTvShow {
    @SerializedName("page")
    var page: Int? = 0

    @SerializedName("total_results")
    var total_results: Long? = 0

    @SerializedName("total_pages")
    var total_pages: Long? = 0

    @SerializedName("results")
    var results: List<ResultTvShow>? = null

    @Entity(tableName = "tv_show_db")
    @Parcelize
    data class ResultTvShow (
        @PrimaryKey
        @SerializedName(StringResponseTvShow.id)
        var id: Long = 0,

        @SerializedName(StringResponseTvShow.original_name)
        var original_name: String = "",

        @SerializedName(StringResponseTvShow.poster_path)
        var poster_path: String = "",

        @SerializedName(StringResponseTvShow.overview)
        var overview: String = "",

        @SerializedName(StringResponseTvShow.first_air)
        var first_air: String = "",

        @SerializedName(StringResponseTvShow.popularity)
        var popularity: Double = 0.0,

        @SerializedName(StringResponseTvShow.vote_average)
        var vote_average: Double= 0.0
    ) : Parcelable{
        fun values(): ContentValues {
            val contentValues = ContentValues()
            contentValues.put(StringResponseTvShow.id, id)
            contentValues.put(StringResponseTvShow.original_name, original_name)
            contentValues.put(StringResponseTvShow.poster_path, poster_path)
            contentValues.put(StringResponseTvShow.overview, overview)
            contentValues.put(StringResponseTvShow.first_air, first_air)
            contentValues.put(StringResponseTvShow.popularity, popularity)
            contentValues.put(StringResponseTvShow.vote_average, vote_average)

            return contentValues
        }
    }
}