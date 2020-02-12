package sample.nackun.com.studyfirst.data.source.upbit

import sample.nackun.com.studyfirst.vo.UpbitMarket
import sample.nackun.com.studyfirst.vo.UpbitTicker

interface UpbitDataSource {
    suspend fun requestMarket(): List<UpbitMarket>
    suspend fun requestTicker(markets: String): List<UpbitTicker>
}