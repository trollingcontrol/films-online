package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.contract.MainListContract
import com.trollingcont.filmsonline.model.MainListModelImpl
import dagger.Binds
import dagger.Module

@Module
interface MainListModelModule {

    @Binds
    fun provideMainListModel(impl: MainListModelImpl): MainListContract.Model
}