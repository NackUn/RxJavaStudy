package com.example.navermoviesample.data.remote

import android.annotation.SuppressLint
import com.example.navermoviesample.data.MovieDataSource
import com.example.navermoviesample.network.NaverApi
import com.example.navermoviesample.vo.SearchResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRemoteDataSource(
    private val retrofitService: NaverApi
) : MovieDataSource {
    @SuppressLint("CheckResult")
    override fun requestMovies(searchWord: String): Single<SearchResult> {
        return retrofitService.requestMovies(searchWord)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }

}