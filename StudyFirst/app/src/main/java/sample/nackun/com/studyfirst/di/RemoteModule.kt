package sample.nackun.com.studyfirst.di

import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import sample.nackun.com.studyfirst.data.source.bithumb.BithumbDataSource
import sample.nackun.com.studyfirst.data.source.bithumb.remote.BithumbRemoteDataSource
import sample.nackun.com.studyfirst.data.source.coinone.remote.CoinOneRemoteDataSource
import sample.nackun.com.studyfirst.data.source.upbit.CoinOneDataSource
import sample.nackun.com.studyfirst.data.source.upbit.UpbitDataSource
import sample.nackun.com.studyfirst.data.source.upbit.remote.UpbitRemoteDataSource
import sample.nackun.com.studyfirst.data.api.BithumbApi
import sample.nackun.com.studyfirst.data.api.CoinOneApi
import sample.nackun.com.studyfirst.data.api.UpbitApi

val remoteModule = module {
    single<UpbitApi> { get<Retrofit> { parametersOf("https://api.upbit.com") }.create(UpbitApi::class.java) }
    single<BithumbApi> { get<Retrofit> { parametersOf("https://api.bithumb.com") }.create(BithumbApi::class.java) }
    single<CoinOneApi> { get<Retrofit> { parametersOf("https://api.coinone.co.kr") }.create(CoinOneApi::class.java) }
    single<UpbitDataSource> { UpbitRemoteDataSource(get()) }
    single<BithumbDataSource> { BithumbRemoteDataSource(get()) }
    single<CoinOneDataSource> { CoinOneRemoteDataSource(get()) }
}