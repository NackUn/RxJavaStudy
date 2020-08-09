package com.example.navermoviesample.di

import com.example.navermoviesample.network.NaverApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://openapi.naver.com"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideChain(chain: Interceptor.Chain): Request {
        return chain.request().newBuilder()
            .header("X-Naver-Client-Id", "mPSHgEZRlh0FiYZQW0N3")
            .header("X-Naver-Client-Secret", "rOdScOfgWl")
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(provideChain(it))
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: Converter.Factory,
        rxJava2CallAdapterFactory: CallAdapter.Factory,
        okHttpClient: OkHttpClient
    ): NaverApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
            .create(NaverApi::class.java)
    }
}