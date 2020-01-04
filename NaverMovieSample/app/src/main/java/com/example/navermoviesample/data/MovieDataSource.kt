package com.example.navermoviesample.data

import com.example.navermoviesample.vo.SearchResult
import io.reactivex.Single

interface MovieDataSource {
    fun requestMovies(searchWord: String): Single<SearchResult>
}