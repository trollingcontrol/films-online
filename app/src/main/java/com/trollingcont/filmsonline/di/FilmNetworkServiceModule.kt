package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.repository.network.FilmNetworkService
import com.trollingcont.filmsonline.repository.network.FilmNetworkServiceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface FilmNetworkServiceModule {

    @Binds
    @Singleton
    fun provideFilmNetworkService(impl: FilmNetworkServiceImpl): FilmNetworkService
}