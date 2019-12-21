package com.example.navermoviesample.data.remote

import android.annotation.SuppressLint
import com.example.navermoviesample.data.DataSource
import com.example.navermoviesample.network.NaverApi
import com.example.navermoviesample.vo.MovieItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteDataSource(
    private val retrofitService: NaverApi
) : DataSource {
    @SuppressLint("CheckResult")
    override fun requestMovies(
        searchWord: String,
        onSearchSuccess: (List<MovieItem>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        retrofitService.requestMovies(searchWord)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { searchResult -> onSearchSuccess(searchResult.movieItems) },
                { thowable -> onError(thowable) }
            )
    }

}