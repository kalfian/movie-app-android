package com.kalfian.movieapp.presenter.tvShow

import android.content.Context
import android.content.Intent
import android.util.Log
import com.kalfian.movieapp.BuildConfig
import com.kalfian.movieapp.model.ResponseTvShow
import com.kalfian.movieapp.services.BaseAPI
import com.kalfian.movieapp.view.MainView
import com.kalfian.movieapp.view.ui.tvShow.DetailTvShowActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class TvShowPresenter(val view: MainView.TvShowView): MainView.TvShowPresenter {
    var baseAPI = BaseAPI.create()
    private var compositeDisposable: CompositeDisposable? = null
    private var result: ArrayList<ResponseTvShow.ResultTvShow>? = null

    override fun getTvShow() {
        compositeDisposable = CompositeDisposable()
        compositeDisposable?.add(
            baseAPI.getTvShow(BuildConfig.MOVIE_API_KEY, "en-US")
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribeWith(
                    object : DisposableObserver<ResponseTvShow>() {
                        override fun onComplete() {
                            Log.d("RESPONSE_MOVIE", "Complete")
                        }

                        override fun onNext(t: ResponseTvShow) {
                            view.showData(t.results as ArrayList<ResponseTvShow.ResultTvShow>)
                            Log.d("RESPONSE_MOVIE", "Complete")
                            result = if ( t.results == null ) {
                                ArrayList()
                            } else {
                                t.results as ArrayList<ResponseTvShow.ResultTvShow>
                            }
                        }

                        override fun onError(e: Throwable) {
                            Log.d("RESPONSE_MOVIE", "Error data :"+e.localizedMessage)
                        }

                    }
                )
        )
    }

    override fun goToDetailTvShow(context: Context, position: Int) {
        val intent = Intent(context, DetailTvShowActivity::class.java)
        intent.putExtra("Data", result?.get(position))
        context.startActivity(intent)
    }

}