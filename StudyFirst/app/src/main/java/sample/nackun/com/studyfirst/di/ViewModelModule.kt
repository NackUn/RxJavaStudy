package sample.nackun.com.studyfirst.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sample.nackun.com.studyfirst.presentation.ui.detail.DetailViewModel
import sample.nackun.com.studyfirst.presentation.ui.ticker.TickerViewModel

val viewModelModule = module {
    viewModel { TickerViewModel(get(), get(), get(), get()) }
    viewModel { (currency: String) ->
        DetailViewModel(currency, get(), get(), get())
    }
}