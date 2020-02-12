package sample.nackun.com.studyfirst.data.source.bithumb

import sample.nackun.com.studyfirst.presentation.vo.BithumbResult

interface BithumbDataSource {
    suspend fun requestAllTicker(): BithumbResult
    suspend fun requestTicker(currency: String): BithumbResult
}