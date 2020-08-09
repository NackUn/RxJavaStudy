package com.example.navermoviesample.di

import com.example.navermoviesample.data.MovieDataSource
import com.example.navermoviesample.data.remote.MovieRemoteDataSource
import com.example.navermoviesample.network.NaverApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RemoteModule {
    @Provides
    @ActivityRetainedScoped
    fun provideRemoteModule(naverApi: NaverApi): MovieDataSource {
        return MovieRemoteDataSource(naverApi)
    }
}