package sample.nackun.com.studyfirst.domain.usecase

import com.google.gson.Gson
import sample.nackun.com.studyfirst.data.bithumb.BithumbRepository
import sample.nackun.com.studyfirst.domain.entity.Ticker
import sample.nackun.com.studyfirst.util.toTicker
import sample.nackun.com.studyfirst.vo.BithumbTicker

class GetBithumbTickerUseCase(private val repository: BithumbRepository) {
    suspend operator fun invoke(currency: String): Ticker =
        repository.requestTicker(currency).let {
            val gson = Gson()
            gson.fromJson(it.bithumbData.toString(), BithumbTicker::class.java).apply {
                setMarket(currency)
            }.toTicker()
        }
}