package com.example.navermoviesample.vo

data class SearchResult(
    val display: Int,
    val movieItems: List<MovieItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)