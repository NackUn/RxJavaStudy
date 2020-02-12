package sample.nackun.com.studyfirst.presentation.ticker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import sample.nackun.com.studyfirst.presentation.base.BaseViewModel
import sample.nackun.com.studyfirst.domain.entity.Ticker
import sample.nackun.com.studyfirst.domain.usecase.GetBithumbTickersUseCase
import sample.nackun.com.studyfirst.domain.usecase.GetCoinOneTickersUseCase
import sample.nackun.com.studyfirst.domain.usecase.GetUpbitMarketUseCase
import sample.nackun.com.studyfirst.domain.usecase.GetUpbitTickersUseCase
import sample.nackun.com.studyfirst.presentation.util.TickerFormatter

class TickerViewModel(
    private val getUpbitMarketUseCase: GetUpbitMarketUseCase,
    private val getUpbitTickersUseCase: GetUpbitTickersUseCase,
    private val getBithumbTickersUseCase: GetBithumbTickersUseCase,
    private val getCoinOneTickersUseCase: GetCoinOneTickersUseCase
) : BaseViewModel() {

    private val firstMarketName = "KRW"

    private val _tickers = MutableLiveData<List<Map<String, String>>>()
    val tickers: LiveData<List<Map<String, String>>> get() = _tickers
    private val _selectedMarket = MutableLiveData<String>()
    val selectedMarket: LiveData<String> get() = _selectedMarket
    private val _errMsg = MutableLiveData<Throwable>()
    val errMsg: LiveData<Throwable> get() = _errMsg

    init {
        _tickers.value = mutableListOf()
        _selectedMarket.value = firstMarketName
    }

    private fun onError(t: Throwable) {
        _errMsg.value = t
    }

    private fun onTickersCombine(
        upbitTickers: List<Ticker>,
        bithumbTickers: List<Ticker>,
        coinOneTickers: List<Ticker>
    ) {
        onTickersLoaded(TickerFormatter.combine(upbitTickers, bithumbTickers, coinOneTickers))
    }

    private fun onTickersLoaded(tickers: List<Ticker>) {
        _tickers.value = TickerFormatter.convertTo(tickers)
    }

    fun selectedMarket(marketLike: String) {
        _selectedMarket.value = marketLike
        showTickers(marketLike)
    }

    fun showTickers(marketLike: String?) {
        viewModelScope.launch {
            val upbitMarket = getUpbitMarketUseCase()
            val strUpbitMarket = upbitMarket.filter {
                it.market.startsWith(marketLike ?: "KRW")
            }.joinToString { it.market }
            val upbitTickers = async { getUpbitTickersUseCase(strUpbitMarket) }

            val bithumbTickers = async { getBithumbTickersUseCase() }

            val coinOneTickers = async { getCoinOneTickersUseCase() }

            onTickersCombine(upbitTickers.await(), bithumbTickers.await(), coinOneTickers.await())
        }
    }
}