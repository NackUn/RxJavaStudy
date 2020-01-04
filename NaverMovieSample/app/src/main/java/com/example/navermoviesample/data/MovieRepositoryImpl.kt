package com.example.navermoviesample.data

import com.example.navermoviesample.vo.MovieItem

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieDataSource
) : MovieRepository {
    override fun requestMovies(
        searchWord: String,
        onSearchSuccess: (List<MovieItem>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        movieRemoteDataSource.requestMovies(
            searchWord,
            onSearchSuccess,
            onError
        )
    }
}