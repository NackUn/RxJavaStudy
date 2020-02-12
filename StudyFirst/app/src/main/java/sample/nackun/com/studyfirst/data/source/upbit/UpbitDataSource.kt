package sample.nackun.com.studyfirst.data.source.upbit

import sample.nackun.com.studyfirst.presentation.vo.UpbitMarket
import sample.nackun.com.studyfirst.presentation.vo.UpbitTicker

interface UpbitDataSource {
    suspend fun requestMarket(): List<UpbitMarket>
    suspend fun requestTicker(markets: String): List<UpbitTicker>
}