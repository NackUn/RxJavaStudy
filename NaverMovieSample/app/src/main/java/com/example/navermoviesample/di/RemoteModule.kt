package com.example.navermoviesample.di

import com.example.navermoviesample.data.DataSource
import com.example.navermoviesample.data.remote.RemoteDataSource
import org.koin.dsl.module

val remoteModule = module {
    single<DataSource> { RemoteDataSource(get()) }
}