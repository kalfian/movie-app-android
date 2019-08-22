package com.kalfian.movieapp.presenter.movie

import android.content.Context
import android.content.Intent
import android.util.Log
import com.kalfian.movieapp.BuildConfig
import com.kalfian.movieapp.model.ResponseMovie
import com.kalfian.movieapp.services.BaseAPI
import com.kalfian.movieapp.view.MainView
import com.kalfian.movieapp.view.ui.movie.DetailMovieActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MoviePresenter(val view: MainView.MovieView): MainView.MoviePresenter {
    var baseAPI = BaseAPI.create()
    private var compositeDisposable: CompositeDisposable? = null
    private var result : ArrayList<ResponseMovie.ResultMovie>? = null

    override fun getMovie() {
        view.showLoader()
        compositeDisposable = CompositeDisposable()
        compositeDisposable?.add(
            baseAPI.getMovie(BuildConfig.MOVIE_API_KEY, "en-US")
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribeWith(
                    object : DisposableObserver<ResponseMovie>() {
                        override fun onComplete() {
                            Log.d("RESPONSE_MOVIE", "Complete")
                            view.hideLoader()
                        }

                        override fun onNext(t: ResponseMovie) {
                            view.showData(t.results as ArrayList<ResponseMovie.ResultMovie>)
                            Log.d("RESPONSE_MOVIE", "Complete")
                            result = if ( t.results == null ) {
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
    }

    override fun goToDetailMovie(context: Context, position: Int) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra("Data", result?.get(position))
        context.startActivity(intent)
    }
}