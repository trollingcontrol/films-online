package com.trollingcont.filmsonline.presenter

import com.trollingcont.filmsonline.contract.FilmDescriptionContract
import javax.inject.Inject

class FilmDescriptionPresenterImpl @Inject constructor(
    private val model: FilmDescriptionContract.Model
) : FilmDescriptionContract.Presenter {

    private var view: FilmDescriptionContract.View? = null

    override fun loadFilmDescription(id: Int) {
        model.getFilmById(
            id,
            { film ->
                view?.setFilmDescription(
                    film.localizedName,
                    film.name,
                    film.year,
                    film.rating,
                    film.description
                )

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