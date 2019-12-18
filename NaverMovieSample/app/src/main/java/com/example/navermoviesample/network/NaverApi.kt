package com.example.navermoviesample.network

import com.example.navermoviesample.vo.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApi {
    @GET("v1/search/movie.json")
    fun requestMovies(@Query("query") searchWord: String): Call<SearchResult>
}