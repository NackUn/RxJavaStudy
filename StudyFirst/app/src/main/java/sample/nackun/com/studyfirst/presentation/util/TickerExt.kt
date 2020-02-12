package sample.nackun.com.studyfirst.presentation.util

import android.annotation.SuppressLint
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.domain.entity.Ticker
import sample.nackun.com.studyfirst.presentation.vo.BithumbTicker
import sample.nackun.com.studyfirst.presentation.vo.CoinOneTicker
import sample.nackun.com.studyfirst.presentation.vo.UpbitTicker

fun UpbitTicker.toTicker(): Ticker =
    Ticker(
        this.accTradePrice24h,
        this.changePrice,
        this.market.split("-")[1],
        this.prevClosingPrice,
        this.tradePrice,
        R.drawable.upbit_img
    )

fun BithumbTicker.toTicker(): Ticker =
    Ticker(
        this.accTradeValue24H,
        this.fluctate24H,
        this.getMarket(),
        this.prevClosingPrice,
        this.closingPrice,
        R.drawable.bithumb_img
    )

@SuppressLint("DefaultLocale")
fun CoinOneTicker.toTicker(): Ticker =
    Ticker(
        this.volume.toDouble() * this.last.toDouble(),
        this.first.toDouble() - this.last.toDouble(),
        this.currency.toUpperCase(),
        this.yesterdayLast.toDouble(),
        this.last.toDouble(),
        R.drawable.coinone_img
    )