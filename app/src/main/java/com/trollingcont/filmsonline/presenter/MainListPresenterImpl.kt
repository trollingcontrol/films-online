package com.trollingcont.filmsonline.presenter

import android.graphics.Bitmap
import android.util.Log
import com.trollingcont.filmsonline.contract.MainListContract
import com.trollingcont.filmsonline.model.Film
import javax.inject.Inject

class MainListPresenterImpl @Inject constructor(
    private val mainListModel: MainListContract.Model
) : MainListContract.Presenter {

    private var view: MainListContract.View? = null

    private lateinit var filmsList: List<Film>

    override fun attach(view: MainListContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun selectGenre(genre: String) {
        view?.updateFilmPreviewsList(generateFilmPreviews(filmsList, genre))
    }

    override fun loadMainList() {
        mainListModel.getFilms(
            { filmsList ->
                this.filmsList = filmsList
                view?.updateGenresList(generateGenresList(filmsList))
                view?.updateFilmPreviewsList(generateFilmPreviews(filmsList, null))
            },
            {

            }
        )
    }

    private fun generateGenresList(films: List<Film>): List<String> {

        val genresList: MutableList<String> = mutableListOf()

        for (film in films) {
            for (genre in film.genres) {
                if (!genresList.contains(genre)) {
                    genresList.add(genre)
                }
            }
        }

        // Sort list by alphabet

        return genresList
    }

    private fun generateFilmPreviews(films: List<Film>, genre: String?): List<Pair<String, Bitmap?>> {

        val filmPreviewsList: MutableList<Pair<String, Bitmap?>> = mutableListOf()

        if (genre == null) {
            for (film in films) {

                if (film.imageUrl != null) {
                    mainListModel.getFilmImageByUrl(
                        film.imageUrl,
                        { bitmap -> filmPreviewsList.add(Pair(film.name, bitmap)) },
                        { filmPreviewsList.add(Pair(film.name, null)) }
                    )
                }
                else {
                    filmPreviewsList.add(Pair(film.name, null))
                }
            }
        }
        else {
            for (film in films) {

                if (film.imageUrl != null) {
                    if (film.genres.contains(genre)) {
                        mainListModel.getFilmImageByUrl(
                            film.imageUrl,
                            { bitmap -> filmPreviewsList.add(Pair(film.name, bitmap)) },
                            { filmPreviewsList.add(Pair(film.name, null)) }
                        )
                    }
                }
                else {
                    filmPreviewsList.add(Pair(film.name, null))
                }
            }
        }

        return filmPreviewsList
    }
}