package com.example.navermoviesample.data

import com.example.navermoviesample.vo.MovieItem

interface MovieRepository {
    fun requestMovies(
        searchWord: String,
        onSearchSuccess: (List<MovieItem>) -> Unit,
        onError: (Throwable) -> Unit
    )
}