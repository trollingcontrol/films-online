package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.contract.MainListContract
import com.trollingcont.filmsonline.presenter.MainListPresenterImpl
import dagger.Binds
import dagger.Module

@Module
interface MainListPresenterModule {

    @Binds
    fun provideMainListPresenter(impl: MainListPresenterImpl): MainListContract.Presenter
}