package com.trollingcont.filmsonline.repository.datasource.local

import android.graphics.Bitmap
import com.trollingcont.filmsonline.model.Film

interface LocalDataSource {
    fun getFilms(): List<Film>?

    fun setFilms(films: List<Film>?)

    fun getBitmapByUrl(imageUrl: String): Bitmap?

    fun setBitmapByUrl(imageUrl: String, bitmap: Bitmap)
}