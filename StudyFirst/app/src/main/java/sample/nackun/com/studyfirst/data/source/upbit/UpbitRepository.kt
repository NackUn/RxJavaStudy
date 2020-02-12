package sample.nackun.com.studyfirst.data.source.upbit

import sample.nackun.com.studyfirst.data.model.UpbitMarket
import sample.nackun.com.studyfirst.data.model.UpbitTicker

interface UpbitRepository {
    suspend fun requestMarket(): List<UpbitMarket>
    suspend fun requestTicker(markets: String): List<UpbitTicker>
}