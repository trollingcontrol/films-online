package com.trollingcont.filmsonline.repository.datasource.remote

import android.graphics.Bitmap
import com.trollingcont.filmsonline.model.Film

interface RemoteDataSource {
    fun getFilms(
        onSuccess: (List<Film>) -> Unit,
        onFailure: () -> Unit
    )

    fun getImageByUrl(
        imageUrl: String,
        onSuccess: (Bitmap) -> Unit,
        onFailure: () -> Unit
    )
}