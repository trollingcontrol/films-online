package com.trollingcont.filmsonline.di

import com.trollingcont.filmsonline.view.FilmDescriptionFragment
import dagger.Subcomponent

@Subcomponent
interface FilmDescriptionComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FilmDescriptionComponent
    }

    fun inject(filmDescriptionFragment: FilmDescriptionFragment)
}