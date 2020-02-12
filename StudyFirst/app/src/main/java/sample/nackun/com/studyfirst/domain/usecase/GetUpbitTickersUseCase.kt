package sample.nackun.com.studyfirst.domain.usecase

import sample.nackun.com.studyfirst.data.source.upbit.UpbitRepository
import sample.nackun.com.studyfirst.domain.entity.Ticker
import sample.nackun.com.studyfirst.presentation.util.toTicker

class GetUpbitTickersUseCase(private val repository: UpbitRepository) {
    suspend operator fun invoke(market: String): List<Ticker> =
        repository.requestTicker(market).map { it.toTicker() }
}