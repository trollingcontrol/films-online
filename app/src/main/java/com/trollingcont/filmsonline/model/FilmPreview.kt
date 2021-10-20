package com.trollingcont.filmsonline.model

import android.graphics.Bitmap

data class FilmPreview(
    val id: Int,
    val name: String?,
    var bitmap: Bitmap?
)
