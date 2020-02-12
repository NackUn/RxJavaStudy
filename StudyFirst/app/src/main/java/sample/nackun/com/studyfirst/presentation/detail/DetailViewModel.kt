package sample.nackun.com.studyfirst.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import sample.nackun.com.studyfirst.presentation.base.BaseViewModel
import sample.nackun.com.studyfirst.domain.entity.Ticker
import sample.nackun.com.studyfirst.domain.usecase.GetBithumbTickerUseCase
import sample.nackun.com.studyfirst.domain.usecase.GetCoinOneTickerUseCase
import sample.nackun.com.studyfirst.domain.usecase.GetUpbitTickersUseCase
import sample.nackun.com.studyfirst.util.TickerFormatter

class DetailViewModel(
    private val tickerName: String,
    private val getUpbitTickersUseCase: GetUpbitTickersUseCase,
    private val getBithumbTickerUseCase: GetBithumbTickerUseCase,
    private val getCoinOneTickerUseCase: GetCoinOneTickerUseCase
) : BaseViewModel() {
    private val _tickers = MutableLiveData<List<Map<String, String>>>()
    val tickers: LiveData<List<Map<String, String>>> get() = _tickers
    private val _errMsg = MutableLiveData<Throwable>()
    val errMsg: LiveData<Throwable> get() = _errMsg

    init {
        _tickers.value = mutableListOf()
    }

    private fun onError(t: Throwable) {
        _errMsg.value = t
    }

    private fun onTickersLoaded(tickers: List<Ticker>) {
        _tickers.value = TickerFormatter.convertTo(tickers)
    }

    fun showTickers() {
        viewModelScope.launch {
            val tickers: MutableList<Ticker> = mutableListOf()

            async {
                try {
                    getUpbitTickersUseCase("KRW-" + tickerName)
                } catch (cause: Throwable) {
                    return@async null
                }
            }.await()?.let {
                tickers.add(it.last())
            }

            async {
                try {
                    getBithumbTickerUseCase(tickerName)
                } catch (cause: Throwable) {
                    return@async null
                }
            }.await()?.let {
                tickers.add(it)
            }

            async {
                try {
                    getCoinOneTickerUseCase(tickerName)
                } catch (cause: Throwable) {
                    return@async null
                }
            }.await()?.let {
                tickers.add(it)
            }

            onTickersLoaded(tickers)
        }
    }
}