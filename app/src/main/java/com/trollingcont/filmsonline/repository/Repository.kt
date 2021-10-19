package com.trollingcont.filmsonline.repository

import android.graphics.Bitmap
import com.trollingcont.filmsonline.model.Film

interface Repository {
    fun getFilms(
        onSuccess: (List<Film>) -> Unit,
        onFailure: () -> Unit
    )

    fun getBitmapByUrl(
        imageUrl: String,
        onSuccess: (Bitmap) -> Unit,
        onFailure: () -> Unit
    )
}