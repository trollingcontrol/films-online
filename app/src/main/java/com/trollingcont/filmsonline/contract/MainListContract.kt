package com.trollingcont.filmsonline.contract

import android.graphics.Bitmap
import com.trollingcont.filmsonline.model.Film

sealed interface MainListContract {

    interface View {
        fun updateGenresList(genres: List<String>)

        fun updateFilmPreviewsList(filmPreviews: List<Pair<String, Bitmap?>>)
    }

    interface Presenter {
        fun selectGenre(genre: String)

        fun loadMainList()

        fun attach(view: View)

        fun detach()
    }

    interface Model {
        fun getFilms(
            onSuccess: (List<Film>) -> Unit,
            onFailure: () -> Unit
        )

        fun getFilmImageByUrl(
            imageUrl: String,
            onSuccess: (Bitmap) -> Unit,
            onFailure: () -> Unit
        )
    }
}