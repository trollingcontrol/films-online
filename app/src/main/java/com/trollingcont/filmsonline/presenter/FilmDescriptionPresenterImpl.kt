package com.trollingcont.filmsonline.presenter

import android.util.Log
import com.trollingcont.filmsonline.contract.FilmDescriptionContract
import javax.inject.Inject

class FilmDescriptionPresenterImpl @Inject constructor(
    private val model: FilmDescriptionContract.Model
) : FilmDescriptionContract.Presenter {

    private var view: FilmDescriptionContract.View? = null

    override fun loadFilmDescription(id: Int) {

        val filmIdToLoad = if (id == -1) {
            model.getFilmId()
        }
        else {
            id
        }

        model.getFilmById(
            filmIdToLoad,
            { film ->
                view?.setFilmDescription(
                    film.localizedName,
                    film.name,
                    film.year,
                    film.rating,
                    film.description
                )

                model.setFilmId(filmIdToLoad)

                if (film.imageUrl != null) {
                    generateFilmBitmap(film.imageUrl)
                }
            },
            { }
        )
    }

    override fun attach(view: FilmDescriptionContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    private fun generateFilmBitmap(imageUrl: String) {
        model.getFilmImageByUrl(
            imageUrl,
            { bitmap ->
                view?.setFilmBitmap(bitmap)
            },
            { }
        )
    }
}