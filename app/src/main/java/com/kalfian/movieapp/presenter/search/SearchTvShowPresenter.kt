package com.kalfian.movieapp.presenter.search

import android.content.Context
import android.content.Intent
import android.util.Log
import com.kalfian.movieapp.BuildConfig
import com.kalfian.movieapp.model.ResponseTvShow
import com.kalfian.movieapp.services.BaseAPI
import com.kalfian.movieapp.view.SearchView
import com.kalfian.movieapp.view.ui.tvShow.DetailTvShowActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class SearchTvShowPresenter(val view: SearchView.SearchTvShowView): SearchView.SearchTvShowPresenter {
    var baseAPI = BaseAPI.create()
    private var compositeDisposable: CompositeDisposable? = null
    private var result: ArrayList<ResponseTvShow.ResultTvShow>? = null

    override fun getData(query: String) {
        view.showLoader()
        compositeDisposable = CompositeDisposable()
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
                            view.showData(t.results as ArrayList<ResponseTvShow.ResultTvShow>)
                            Log.d("RESPONSE_MOVIE", "Complete")
                            result = if ( t.results == null ) {
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

    override fun toDetail(context: Context, position: Int) {
        val intent = Intent(context, DetailTvShowActivity::class.java)
        intent.putExtra("Data", result?.get(position))
        context.startActivity(intent)
    }

}