package com.kalfian.movieapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class ResponseMovie {
    @SerializedName("page")
    var page: Int? = 0

    @SerializedName("total_results")
    var total_results: Long? = 0

    @SerializedName("total_pages")
    var total_pages: Long? = 0

    @SerializedName("results")
    var results: List<ResultMovie>? = null

    @Parcelize
    data class ResultMovie (
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