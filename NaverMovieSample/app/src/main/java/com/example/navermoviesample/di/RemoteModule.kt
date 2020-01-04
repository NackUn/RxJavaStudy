package com.example.navermoviesample.di

import com.example.navermoviesample.data.MovieDataSource
import com.example.navermoviesample.data.remote.MovieRemoteDataSource
import org.koin.dsl.module

val remoteModule = module {
    single<MovieDataSource> { MovieRemoteDataSource(get()) }
}