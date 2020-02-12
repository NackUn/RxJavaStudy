package sample.nackun.com.studyfirst.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import sample.nackun.com.studyfirst.data.model.CoinOneTicker

interface CoinOneApi {
    @GET("/ticker?currency=all")
    suspend fun requestAllTicker(): Map<String, Any>

    @GET("/ticker")
    suspend fun requestTicker(@Query("currency") currency: String): CoinOneTicker
}