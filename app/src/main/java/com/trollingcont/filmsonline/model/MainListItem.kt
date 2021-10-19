package com.trollingcont.filmsonline.model

import android.graphics.drawable.Drawable

sealed class MainListItem {
    class Genre(val genreName: String) : MainListItem()
    class Title(val titleName: String) : MainListItem()
    class FilmsList(val itemsList: List<Pair<String, Drawable>>) : MainListItem()
}
