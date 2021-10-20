package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.view.FilmDescriptionFragment
import com.trollingcont.filmsonline.view.MainActivity
import com.trollingcont.filmsonline.view.MainListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FilmNetworkServiceModule::class,
        RepositoryModule::class,
        LocalDataSourceModule::class,
        RemoteDataSourceModule::class,
        SubcomponentsModule::class,
        MainListPresenterModule::class,
        MainListModelModule::class,
        FilmDescriptionModelModule::class,
        FilmDescriptionPresenterModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(mainListFragment: MainListFragment)
    fun inject(filmDescriptionFragment: FilmDescriptionFragment)
    fun mainListComponent(): MainListComponent.Factory
    fun filmDescriptionComponent(): FilmDescriptionComponent.Factory
}