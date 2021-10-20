package com.trollingcont.filmsonline.repository.datasource.remote

import android.graphics.Bitmap
import com.trollingcont.filmsonline.model.Film

interface RemoteDataSource {
    fun getFilms(
        onSuccess: (List<Film>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getImageByUrl(
        imageUrl: String,
        onSuccess: (Bitmap) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}