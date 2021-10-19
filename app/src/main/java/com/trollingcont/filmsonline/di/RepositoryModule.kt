package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.repository.Repository
import com.trollingcont.filmsonline.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideRepository(impl: RepositoryImpl): Repository
}