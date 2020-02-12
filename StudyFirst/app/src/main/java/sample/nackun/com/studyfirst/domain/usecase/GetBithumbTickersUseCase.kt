package sample.nackun.com.studyfirst.domain.usecase

import com.google.gson.Gson
import sample.nackun.com.studyfirst.data.source.bithumb.BithumbRepository
import sample.nackun.com.studyfirst.domain.entity.Ticker
import sample.nackun.com.studyfirst.util.toTicker
import sample.nackun.com.studyfirst.vo.BithumbTicker

class GetBithumbTickersUseCase(private val repository: BithumbRepository) {
    suspend operator fun invoke(): List<Ticker> =
        repository.requestAllTicker().let {
            it.bithumbData.filterKeys {
                !it.equals("date")
            }.map { bithumbTicker ->
                val gson = Gson()
                gson.fromJson(bithumbTicker.value.toString(), BithumbTicker::class.java).apply {
                    setMarket(bithumbTicker.key)
                }
            }.map { bithumbTicker ->
                bithumbTicker.toTicker()
            }
        }
}