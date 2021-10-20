package com.trollingcont.filmsonline.model

import android.graphics.Bitmap
import com.trollingcont.filmsonline.contract.FilmDescriptionContract
import com.trollingcont.filmsonline.repository.Repository
import java.lang.IllegalStateException
import javax.inject.Inject

class FilmDescriptionModelImpl @Inject constructor(
    private val repository: Repository
) : FilmDescriptionContract.Model {

    private var filmId: Int = -1

    override fun getFilmById(
        id: Int,
        onSuccess: (Film) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        repository.getFilms(
            { filmsList ->
                val filmById = getFilmById(filmsList, id)
                if (filmById != null) {
                    onSuccess(filmById)
                } else {
                    onFailure(IllegalStateException("Film with id $id not found"))
                }
            },
            { throwable -> onFailure(throwable) }
        )
    }

    override fun getFilmImageByUrl(
        imageUrl: String,
        onSuccess: (Bitmap) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        repository.getBitmapByUrl(imageUrl, onSuccess, onFailure)
    }

    override fun setFilmId(id: Int) {
        this.filmId = id
    }

    override fun getFilmId(): Int =
        this.filmId

    private fun getFilmById(filmsList: List<Film>, id: Int): Film? {

        for (film in filmsList) {
            if (film.id == id) {
                return film
            }
        }

        return null
    }
}