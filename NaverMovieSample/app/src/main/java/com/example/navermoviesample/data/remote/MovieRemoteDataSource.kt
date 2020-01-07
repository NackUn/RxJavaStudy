package com.example.navermoviesample.data.remote

import android.annotation.SuppressLint
import com.example.navermoviesample.data.MovieDataSource
import com.example.navermoviesample.network.NaverApi
import com.example.navermoviesample.vo.SearchResult
import io.reactivex.Single

class MovieRemoteDataSource(
    private val retrofitService: NaverApi
) : MovieDataSource {
    @SuppressLint("CheckResult")
    override fun requestMovies(searchWord: String): Single<SearchResult> =
        retrofitService.requestMovies(searchWord)
}