package sample.nackun.com.studyfirst.data.source.upbit

import sample.nackun.com.studyfirst.data.model.CoinOneTicker

interface CoinOneDataSource {
    suspend fun requestAllTicker(): Map<String, Any>
    suspend fun requestTicker(currency: String): CoinOneTicker
}