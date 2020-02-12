package sample.nackun.com.studyfirst.domain.usecase

import com.google.gson.Gson
import sample.nackun.com.studyfirst.data.source.bithumb.BithumbRepository
import sample.nackun.com.studyfirst.domain.entity.Ticker
import sample.nackun.com.studyfirst.presentation.util.toTicker
import sample.nackun.com.studyfirst.data.model.BithumbTicker

class GetBithumbTickerUseCase(private val repository: BithumbRepository) {
    suspend operator fun invoke(currency: String): Ticker =
        repository.requestTicker(currency).let {
            val gson = Gson()
            gson.fromJson(it.bithumbData.toString(), BithumbTicker::class.java).apply {
                setMarket(currency)
            }.toTicker()
        }
}