package com.trollingcont.filmsonline.model

import com.google.gson.annotations.SerializedName

data class FilmList(
    @SerializedName("films")
    val films: List<Film>
)
