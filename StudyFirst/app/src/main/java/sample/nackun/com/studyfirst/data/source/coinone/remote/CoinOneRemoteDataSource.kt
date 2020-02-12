package sample.nackun.com.studyfirst.data.source.coinone.remote

import sample.nackun.com.studyfirst.data.source.upbit.CoinOneDataSource
import sample.nackun.com.studyfirst.data.api.CoinOneApi
import sample.nackun.com.studyfirst.vo.CoinOneTicker

class CoinOneRemoteDataSource(
    private val retrofitService: CoinOneApi
) : CoinOneDataSource {
    override suspend fun requestTicker(currency: String): CoinOneTicker =
        retrofitService.requestTicker(currency)

    override suspend fun requestAllTicker() =
        retrofitService.requestAllTicker()
}