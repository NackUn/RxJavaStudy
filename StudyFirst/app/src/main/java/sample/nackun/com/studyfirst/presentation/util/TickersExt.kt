package sample.nackun.com.studyfirst.presentation.util

import sample.nackun.com.studyfirst.domain.entity.Ticker

fun List<Ticker>.toPresentation(): List<sample.nackun.com.studyfirst.presentation.model.Ticker> =
    this.map {
        sample.nackun.com.studyfirst.presentation.model.Ticker(
            it.accTradePrice24h,
            it.changePrice,
            it.market,
            it.prevClosingPrice,
            it.tradePrice,
            it.exchangeImg
        )
    }