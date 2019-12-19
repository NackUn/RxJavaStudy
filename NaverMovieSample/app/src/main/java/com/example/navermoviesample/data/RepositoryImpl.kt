package com.example.navermoviesample.data

import com.example.navermoviesample.vo.MovieItem

class RepositoryImpl(
    private val remoteDataSource: DataSource
) : Repository {
    override fun requestMovies(
        searchWord: String,
        onSearchSuccess: (List<MovieItem>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        remoteDataSource.requestMovies(
            searchWord,
            onSearchSuccess,
            onError
        )
    }
}