package sample.nackun.com.studyfirst.data.source.bithumb

import sample.nackun.com.studyfirst.data.model.BithumbResult

interface BithumbRepository {
    suspend fun requestAllTicker(): BithumbResult
    suspend fun requestTicker(currency: String): BithumbResult
}