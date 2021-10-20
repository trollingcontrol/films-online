package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.contract.MainListContract
import com.trollingcont.filmsonline.model.MainListModelImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface MainListModelModule {

    @Binds
    @Singleton
    fun provideMainListModel(impl: MainListModelImpl): MainListContract.Model
}