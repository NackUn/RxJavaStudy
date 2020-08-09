package com.example.navermoviesample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@Suppress("unused")
@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}