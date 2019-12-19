package com.example.navermoviesample.data

import com.example.navermoviesample.vo.MovieItem

class Repository private constructor(
    private val remoteDataSource: DataSource
) : DataSource {
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

    companion object {
        private var INSTANCE: Repository? = null

        operator fun invoke(RemoteDataSource: DataSource) =
            INSTANCE ?: Repository(RemoteDataSource)
                .apply { INSTANCE = this }
    }
}