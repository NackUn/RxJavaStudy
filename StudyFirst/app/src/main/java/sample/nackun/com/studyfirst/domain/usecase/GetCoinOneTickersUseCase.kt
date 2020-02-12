package sample.nackun.com.studyfirst.domain.usecase

import com.google.gson.Gson
import sample.nackun.com.studyfirst.data.source.upbit.CoinOneRepository
import sample.nackun.com.studyfirst.domain.entity.Ticker
import sample.nackun.com.studyfirst.util.toTicker
import sample.nackun.com.studyfirst.presentation.vo.CoinOneTicker

class GetCoinOneTickersUseCase(private val repository: CoinOneRepository) {
    suspend operator fun invoke(): List<Ticker> =
        repository.requestAllTicker().filter {
            !(it.key == "result" || it.key == "errorCode" || it.key == "timestamp")
        }.map {
            val gson = Gson()
            gson.fromJson(it.value.toString(), CoinOneTicker::class.java)
        }.map {
            it.toTicker()
        }
}