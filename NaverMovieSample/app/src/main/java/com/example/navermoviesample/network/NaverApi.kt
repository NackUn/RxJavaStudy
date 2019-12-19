package com.example.navermoviesample.network

import com.example.navermoviesample.vo.SearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverApi {
    @Headers(
        "X-Naver-Client-Id:mPSHgEZRlh0FiYZQW0N3",
        "X-Naver-Client-Secret:rOdScOfgWl"
    )

    @GET("v1/search/movie.json")
    fun requestMovies(
        @Query("query") searchWord: String
    ): Single<SearchResult>
}