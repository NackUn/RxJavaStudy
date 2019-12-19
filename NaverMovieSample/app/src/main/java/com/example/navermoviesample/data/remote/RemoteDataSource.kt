package com.example.navermoviesample.data.remote

import com.example.navermoviesample.data.DataSource
import com.example.navermoviesample.network.NaverApi
import com.example.navermoviesample.vo.MovieItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteDataSource(
    private val retrofitService: NaverApi
) : DataSource {
    override fun requestMovies(
        searchWord: String,
        onSearchSuccess: (List<MovieItem>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        retrofitService.run {
            this.requestMovies(searchWord)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { searchResult -> onSearchSuccess },
                    { thowable -> thowable.message }
                )
        }
    }

}