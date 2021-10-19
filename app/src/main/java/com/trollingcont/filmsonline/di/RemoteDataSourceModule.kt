package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.repository.datasource.remote.RemoteDataSource
import com.trollingcont.filmsonline.repository.datasource.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RemoteDataSourceModule {

    @Binds
    @Singleton
    fun provideRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource
}