package sample.nackun.com.studyfirst

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import sample.nackun.com.studyfirst.data.di.networkModule
import sample.nackun.com.studyfirst.data.di.remoteModule
import sample.nackun.com.studyfirst.data.di.repositoryModule
import sample.nackun.com.studyfirst.domain.di.useCaseModule
import sample.nackun.com.studyfirst.presentation.di.viewModelModule

@Suppress("unused")
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    useCaseModule,
                    networkModule,
                    repositoryModule,
                    remoteModule,
                    viewModelModule
                )
            )
        }
    }
}