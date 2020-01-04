package com.example.navermoviesample.di

import com.example.navermoviesample.data.MovieRepository
import com.example.navermoviesample.data.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}