package com.trollingcont.filmsonline.repository.datasource.local

import android.graphics.Bitmap
import com.trollingcont.filmsonline.model.Film
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor() : LocalDataSource {

    private var films: List<Film>? = null
    private var bitmaps: HashMap<String, Bitmap> = HashMap()

    override fun getFilms(): List<Film>? = films

    override fun setFilms(films: List<Film>?) {
        this.films = films
    }

    override fun getBitmapByUrl(imageUrl: String): Bitmap? =
        bitmaps[imageUrl]

    override fun setBitmapByUrl(imageUrl: String, bitmap: Bitmap) {
        bitmaps[imageUrl] = bitmap
    }
}