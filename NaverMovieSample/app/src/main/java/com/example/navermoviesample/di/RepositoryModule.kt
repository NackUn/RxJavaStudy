package com.example.navermoviesample.di

import com.example.navermoviesample.data.Repository
import com.example.navermoviesample.data.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get()) }
}