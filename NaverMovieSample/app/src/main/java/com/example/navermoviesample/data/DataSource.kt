package com.example.navermoviesample.data

import com.example.navermoviesample.vo.MovieItem

interface DataSource {
    fun requestMovies(
        searchWord: String,
        onSearchSuccess: (List<MovieItem>) -> Unit,
        onError: (Throwable) -> Unit
    )
}