package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.repository.datasource.local.LocalDataSource
import com.trollingcont.filmsonline.repository.datasource.local.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun provideLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource
}