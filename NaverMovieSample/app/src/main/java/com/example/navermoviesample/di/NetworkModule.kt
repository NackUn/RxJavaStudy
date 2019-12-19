package com.example.navermoviesample.di

import com.example.navermoviesample.network.NaverApi
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun getNetworkModule(baseUrl: String) = module {
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
            .create(NaverApi::class.java)
    }

    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }
}