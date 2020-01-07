package com.example.navermoviesample.di

import com.example.navermoviesample.network.NaverApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.parameter.parametersOf
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
            .client(get())
            .build()
            .create(NaverApi::class.java)
    }

    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(get { parametersOf(it) })
            }
            .build()
    }

    factory { (chain: Interceptor.Chain) ->
        chain.request().newBuilder()
            .header("X-Naver-Client-Id", "mPSHgEZRlh0FiYZQW0N3")
            .header("X-Naver-Client-Secret", "rOdScOfgWl")
            .build()
    }
}