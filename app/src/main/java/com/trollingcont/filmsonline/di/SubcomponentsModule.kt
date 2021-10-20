package com.trollingcont.filmsonline.di

import dagger.Module

@Module(
    subcomponents =
    [
        MainListComponent::class,
        FilmDescriptionComponent::class
    ]
)
interface SubcomponentsModule