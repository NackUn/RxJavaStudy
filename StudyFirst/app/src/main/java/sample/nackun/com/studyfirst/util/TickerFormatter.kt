package sample.nackun.com.studyfirst.util

import android.annotation.SuppressLint
import android.graphics.Color
import sample.nackun.com.studyfirst.domain.entity.Ticker

object TickerFormatter {

    @SuppressLint("DefaultLocale")
    fun combine(
        upbitTickers: List<Ticker>,
        bithumbTickers: List<Ticker>,
        coinOneTickers: List<Ticker>
    ): List<Ticker> {
        val combineList = mutableListOf<Ticker>()
        combineList.clear()

        val markets = mutableListOf<String>()

        upbitTickers.forEach {
            markets.add(it.market)
        }
        bithumbTickers.forEach {
            if (!markets.contains(it.market)) {
                markets.add(it.market)
            }
        }
        coinOneTickers.forEach {
            if (!markets.contains(it.market)) {
                markets.add(it.market)
            }
        }

        val emptyTicker = Ticker(
            0.0,
            0.0,
            "",
            0.0,
            0.0,
            0
        )

        markets.forEach { market ->
            val upbitTicker = upbitTickers.find {
                it.market.equals(market)
            } ?: emptyTicker

            val bithumbTicker = bithumbTickers.find {
                it.market.equals(market)
            } ?: emptyTicker

            val coinOneTicker = coinOneTickers.find {
                it.market.equals(market)
            } ?: emptyTicker

            val a = upbitTicker.accTradePrice24h
            val b = bithumbTicker.accTradePrice24h
            val c = coinOneTicker.accTradePrice24h

            if (a > b) {
                if (a > c) {
                    combineList.add(upbitTicker)
                } else {
                    combineList.add(coinOneTicker)
                }
            } else {
                if (b > c) {
                    combineList.add(bithumbTicker)
                } else {
                    combineList.add(coinOneTicker)
                }
            }
        }

        return combineList
    }

    fun convertTo(target: List<Ticker>): List<Map<String, String>> {
        val convertList = mutableListOf<Map<String, String>>()
        convertList.clear()

        target.forEachIndexed { index, ticker ->
            convertList.add(
                index,
                hashMapOf(
                    "tickerName" to getTickerName(ticker.market),
                    "currentPrice" to getCurrentPrice(ticker.tradePrice),
                    "comparePrice" to getComparePrice(ticker.tradePrice, ticker.prevClosingPrice),
                    "compareColor" to getCompareColor(ticker.tradePrice, ticker.prevClosingPrice),
                    "changePrice" to getChangePrice(ticker.accTradePrice24h),
                    "exchangeImg" to getMarketImgSrc(ticker.exchangeImg)
                )
            )
        }
        return convertList
    }

    private fun getMarketImgSrc(exchangeImg: Int) =
        exchangeImg.toString()

    private fun getTickerName(tickerName: String) =
        tickerName.substring(tickerName.indexOf("-") + 1, tickerName.length)

    private fun getCurrentPrice(currentPrice: Double): String {
        return when {
            currentPrice > 10 -> String.format("%,d", currentPrice.toInt())
            currentPrice > 1 -> String.format("%,.2f", currentPrice)
            currentPrice < 1 -> String.format("%,.8f", currentPrice)
            else -> String.format("%,d", currentPrice.toInt())
        }
    }

    private fun getComparePrice(currentPrice: Double, beforePrice: Double) =
        String.format("%.2f", ((currentPrice - beforePrice) / beforePrice * 100)) + "%"

    private fun getCompareColor(currentPrice: Double, beforePrice: Double): String {
        val differPrice = (currentPrice - beforePrice) / beforePrice * 100
        return when {
            differPrice > 0 -> Color.RED.toString()
            differPrice < 0 -> Color.BLUE.toString()
            else -> Color.BLACK.toString()
        }
    }

    private fun getChangePrice(changePrice: Double): String {
        return when {
            changePrice / 1_000_000 > 50 ->
                String.format("%,d", (changePrice / 1_000_000).toInt()) + " M"
            changePrice / 1_000 > 1_000 ->
                String.format("%,d", (changePrice / 1_000).toInt()) + " K"
            changePrice > 1_000 ->
                String.format("%,d", changePrice.toInt())
            else ->
                String.format("%,.3f", changePrice)
        }
    }
}