package com.example.navermoviesample

import android.app.Application
import com.example.navermoviesample.di.getNetworkModule
import com.example.navermoviesample.di.remoteModule
import com.example.navermoviesample.di.repositoryModule
import com.example.navermoviesample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    getNetworkModule("https://openapi.naver.com"),
                    repositoryModule,
                    remoteModule,
                    viewModelModule
                )
            )
        }
    }
}