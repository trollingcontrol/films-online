package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.view.MainListFragment
import dagger.Subcomponent

@Subcomponent
interface MainListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainListComponent
    }

    fun inject(mainListFragment: MainListFragment)
}