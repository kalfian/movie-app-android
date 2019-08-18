package com.kalfian.movieapp.services

import com.google.gson.GsonBuilder
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.model.ResponseTvShow
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface BaseAPI {

    @GET("discover/movie?")
    fun getMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Observable<ResponseMovie>

    @GET("discover/tv?")
    fun getTvShow(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Observable<ResponseTvShow>

    companion object {
        var URL: String = "https://api.themoviedb.org/3/"
        fun create(): BaseAPI {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val clientBuilder = OkHttpClient.Builder()
            clientBuilder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
            val client = clientBuilder.build()
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(BaseAPI::class.java)
        }
    }
}