package com.trollingcont.filmsonline.contract

import android.graphics.Bitmap
import com.trollingcont.filmsonline.model.Film
import com.trollingcont.filmsonline.model.FilmPreview

sealed interface MainListContract {

    interface View {
        fun updateGenresList(genres: List<String>)

        fun setFilmPreviewList(filmPreviews: List<FilmPreview>)

        fun setFilmPreviewBitmap(filmId: Int, bitmap: Bitmap)

        fun highlightGenre(genreName: String)

        fun removeHighlightFromGenre(genreName: String)

        fun onError(throwable: Throwable)
    }

    interface Presenter {
        fun selectGenre(genreName: String)

        fun loadMainList()

        fun attach(view: View)

        fun detach()
    }

    interface Model {
        fun getFilms(
            onSuccess: (List<Film>) -> Unit,
            onFailure: (Throwable) -> Unit
        )

        fun getFilmImageByUrl(
            imageUrl: String,
            onSuccess: (Bitmap) -> Unit,
            onFailure: (Throwable) -> Unit
        )

        fun getSelectedGenre(): String?

        fun setSelectedGenre(genreName: String)
    }
}