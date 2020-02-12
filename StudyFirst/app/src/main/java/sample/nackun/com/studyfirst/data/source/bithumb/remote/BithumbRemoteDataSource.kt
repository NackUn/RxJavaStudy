package sample.nackun.com.studyfirst.data.source.bithumb.remote

import sample.nackun.com.studyfirst.data.source.bithumb.BithumbDataSource
import sample.nackun.com.studyfirst.data.api.BithumbApi

class BithumbRemoteDataSource(
    private val retrofitService: BithumbApi
) : BithumbDataSource {
    override suspend fun requestTicker(currency: String) =
        retrofitService.requestTicker(currency)

    override suspend fun requestAllTicker() =
        retrofitService.requestAllTicker()
}