package sample.nackun.com.studyfirst.data.di

import org.koin.dsl.module
import sample.nackun.com.studyfirst.data.source.bithumb.BithumbRepository
import sample.nackun.com.studyfirst.data.source.bithumb.BithumbRepositoryImpl
import sample.nackun.com.studyfirst.data.source.upbit.CoinOneRepository
import sample.nackun.com.studyfirst.data.source.upbit.CoinOneRepositoryImpl
import sample.nackun.com.studyfirst.data.source.upbit.UpbitRepository
import sample.nackun.com.studyfirst.data.source.upbit.UpbitRepositoryImpl

val repositoryModule = module {
    single<UpbitRepository> {
        UpbitRepositoryImpl(
            get()
        )
    }
    single<BithumbRepository> {
        BithumbRepositoryImpl(
            get()
        )
    }
    single<CoinOneRepository> {
        CoinOneRepositoryImpl(
            get()
        )
    }
}