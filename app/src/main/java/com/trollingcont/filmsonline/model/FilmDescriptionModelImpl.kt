package com.trollingcont.filmsonline.model

import android.graphics.Bitmap
import com.trollingcont.filmsonline.contract.FilmDescriptionContract
import com.trollingcont.filmsonline.repository.Repository
import javax.inject.Inject

class FilmDescriptionModelImpl @Inject constructor(
    private val repository: Repository
) : FilmDescriptionContract.Model {

    override fun getFilmById(
        id: Int,
        onSuccess: (Film) -> Unit,
        onFailure: () -> Unit
    ) {
        repository.getFilms(
            { filmsList ->
                val filmById = getFilmById(filmsList, id)
                if (filmById != null) {
                    onSuccess(filmById)
                }
                else {
                    onFailure()
                }
            },
            { onFailure() }
        )
    }

    override fun getFilmImageByUrl(
        imageUrl: String,
        onSuccess: (Bitmap) -> Unit,
        onFailure: () -> Unit
    ) {
        repository.getBitmapByUrl(imageUrl, onSuccess, onFailure)
    }

    private fun getFilmById(filmsList: List<Film>, id: Int): Film? {

        for (film in filmsList) {
            if (film.id == id) {
                return film
            }
        }

        return null
    }
}