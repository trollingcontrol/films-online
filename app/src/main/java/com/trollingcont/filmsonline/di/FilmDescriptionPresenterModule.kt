package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.contract.FilmDescriptionContract
import com.trollingcont.filmsonline.presenter.FilmDescriptionPresenterImpl
import dagger.Binds
import dagger.Module

@Module
interface FilmDescriptionPresenterModule {

    @Binds
    fun provideFilmDescriptionPresenter(
        impl: FilmDescriptionPresenterImpl
    ): FilmDescriptionContract.Presenter
}