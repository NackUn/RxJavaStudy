package sample.nackun.com.studyfirst.domain.usecase

import sample.nackun.com.studyfirst.data.source.upbit.CoinOneRepository
import sample.nackun.com.studyfirst.domain.entity.Ticker
import sample.nackun.com.studyfirst.data.util.toTicker

class GetCoinOneTickerUseCase(private val repository: CoinOneRepository) {
    suspend operator fun invoke(currency: String): Ticker =
        repository.requestTicker(currency).toTicker()
}