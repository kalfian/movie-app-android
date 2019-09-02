package com.kalfian.movieapp.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import com.kalfian.movieapp.BuildConfig
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.model.ResponseTvShow
import com.kalfian.movieapp.services.BaseAPI
import com.kalfian.movieapp.view.MainView
import com.kalfian.movieapp.view.ui.movie.DetailMovieActivity
import com.kalfian.movieapp.view.ui.tvShow.DetailTvShowActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class SearchPresenter(val view: MainView.SearchView): MainView.SearchPresenter {

    var baseAPI = BaseAPI.create()
    private var compositeDisposable: CompositeDisposable? = null
    private var resultMovie : ArrayList<ResponseMovie.ResultMovie>? = null
    private var resultTvShow : ArrayList<ResponseTvShow.ResultTvShow>? = null

    override fun getSearch(context: Context, isMovie: Boolean, query: String) {
        view.showLoader()
        compositeDisposable = CompositeDisposable()
        if (isMovie) {
            compositeDisposable?.add(
                baseAPI.searchMovie(BuildConfig.MOVIE_API_KEY, "en-US", query)
                    .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                    .subscribeWith(
                        object : DisposableObserver<ResponseMovie>() {
                            override fun onComplete() {
                                Log.d("RESPONSE_MOVIE", "Complete")
                                view.hideLoader()
                            }

                            override fun onNext(t: ResponseMovie) {
                                view.showData(t.results as ArrayList<Any>)
                                Log.d("RESPONSE_MOVIE", "Complete")
                                resultMovie = if ( t.results == null ) {
                                    ArrayList()
                                } else {
                                    t.results as ArrayList<ResponseMovie.ResultMovie>
                                }
                                view.hideLoader()
                            }

                            override fun onError(e: Throwable) {
                                Log.d("RESPONSE_MOVIE", "Error data :"+e.localizedMessage)
                                view.hideLoader()
                            }
                        }
                    )
            )
        } else {
            compositeDisposable?.add(
                baseAPI.searchTvShow(BuildConfig.MOVIE_API_KEY, "en-US", query)
                    .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                    .subscribeWith(
                        object : DisposableObserver<ResponseTvShow>() {
                            override fun onComplete() {
                                Log.d("RESPONSE_MOVIE", "Complete")
                                view.hideLoader()
                            }

                            override fun onNext(t: ResponseTvShow) {
                                view.showData(t.results as ArrayList<Any>)
                                Log.d("RESPONSE_MOVIE", "Complete")
                                resultTvShow = if ( t.results == null ) {
                                    ArrayList()
                                } else {
                                    t.results as ArrayList<ResponseTvShow.ResultTvShow>
                                }
                                view.hideLoader()
                            }

                            override fun onError(e: Throwable) {
                                Log.d("RESPONSE_MOVIE", "Error data :"+e.localizedMessage)
                                view.hideLoader()
                            }
                        }
                    )
            )
        }
    }

    override fun toDetail(context: Context, position: Int, isMovie: Boolean) {
        if (isMovie) {
            val intent = Intent(context, DetailMovieActivity::class.java)
            intent.putExtra("Data", resultMovie?.get(position))
            context.startActivity(intent)
        } else {
            val intent = Intent(context, DetailTvShowActivity::class.java)
            intent.putExtra("Data", resultTvShow?.get(position))
            context.startActivity(intent)
        }
    }

}