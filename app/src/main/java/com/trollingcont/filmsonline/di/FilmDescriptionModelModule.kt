package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.contract.FilmDescriptionContract
import com.trollingcont.filmsonline.model.FilmDescriptionModelImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface FilmDescriptionModelModule {

    @Binds
    @Singleton
    fun provideFilmDescriptionModel(impl: FilmDescriptionModelImpl): FilmDescriptionContract.Model
}