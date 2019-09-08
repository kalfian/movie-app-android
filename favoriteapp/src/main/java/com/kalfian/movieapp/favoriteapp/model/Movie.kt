package com.kalfian.movieapp.favoriteapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

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
        @SerializedName("id")
        var id: Long = 0,

        @SerializedName("title")
        var title: String = "",

        @SerializedName("poster_path")
        var poster_path: String = "",

        @SerializedName("overview")
        var overview: String = "",

        @SerializedName("release_date")
        var release_date: String = "",

        @SerializedName("popularity")
        var popularity: Double = 0.0,

        @SerializedName("vote_average")
        var vote: Double= 0.0

    ) : Parcelable
}