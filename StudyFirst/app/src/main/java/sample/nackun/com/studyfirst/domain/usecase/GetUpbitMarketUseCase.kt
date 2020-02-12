package sample.nackun.com.studyfirst.domain.usecase

import sample.nackun.com.studyfirst.data.source.upbit.UpbitRepository
import sample.nackun.com.studyfirst.data.model.UpbitMarket

class GetUpbitMarketUseCase(private val repository: UpbitRepository) {
    suspend operator fun invoke(): List<UpbitMarket> = repository.requestMarket()
}