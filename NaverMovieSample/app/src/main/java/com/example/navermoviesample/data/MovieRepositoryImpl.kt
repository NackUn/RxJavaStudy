package com.example.navermoviesample.data

import com.example.navermoviesample.vo.SearchResult
import io.reactivex.Single

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieDataSource
) : MovieRepository {
    override fun requestMovies(searchWord: String): Single<SearchResult> =
        movieRemoteDataSource.requestMovies(searchWord)
}