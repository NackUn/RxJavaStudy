package sample.nackun.com.studyfirst.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import sample.nackun.com.studyfirst.data.model.UpbitMarket
import sample.nackun.com.studyfirst.data.model.UpbitTicker

interface UpbitApi {
    @GET("v1/market/all")
    suspend fun requestMarket(): List<UpbitMarket>

    @GET("v1/ticker/")
    suspend fun requestTicker(@Query("markets") markets: String): List<UpbitTicker>
}