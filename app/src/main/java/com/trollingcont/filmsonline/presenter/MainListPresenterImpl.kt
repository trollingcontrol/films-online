package com.trollingcont.filmsonline.presenter

import com.trollingcont.filmsonline.contract.MainListContract
import com.trollingcont.filmsonline.model.Film
import com.trollingcont.filmsonline.model.FilmPreview
import javax.inject.Inject

class MainListPresenterImpl @Inject constructor(
    private val mainListModel: MainListContract.Model
) : MainListContract.Presenter {

    private var view: MainListContract.View? = null
    private var highlightedGenre: String? = null

    override fun attach(view: MainListContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun loadMainList() {
        mainListModel.getFilms(
            { filmsList ->
                val genresList = getGenres(filmsList)
                generateGenresList(genresList)
                updateFilmPreviewList(filmsList)
            },
            { }
        )
    }

    override fun selectGenre(genreName: String) {
        mainListModel.getFilms(
            { filmsList ->
                val filmsByGenre = getFilmsByGenre(filmsList, genreName)
                updateFilmPreviewList(filmsByGenre)

                if (highlightedGenre != null) {
                    view?.removeHighlightFromGenre(highlightedGenre!!)
                }

                highlightedGenre = genreName
                view?.highlightGenre(genreName)
            },
            { }
        )
    }

    private fun getGenres(films: List<Film>): List<String> {

        val genresList: MutableList<String> = mutableListOf()

        for (film in films) {
            for (genre in film.genres) {
                if (!genresList.contains(genre)) {
                    genresList.add(genre)
                }
            }
        }

        return genresList
    }

    private fun getFilmsByGenre(filmsList: List<Film>, genreName: String): List<Film> {

        val filmsByGenre: MutableList<Film> = mutableListOf()

        for (film in filmsList) {
            if (film.genres.contains(genreName)) {
                filmsByGenre.add(film)
            }
        }

        return filmsByGenre
    }

    private fun generateGenresList(genresList: List<String>) {
        view?.updateGenresList(genresList)
    }

    private fun updateFilmPreviewList(filmsList: List<Film>) {
        generateFilmPreviewList(filmsList)
        generateFilmPreviewListBitmaps(filmsList)
    }

    private fun generateFilmPreviewList(filmsList: List<Film>) {

        val filmPreviews: MutableList<FilmPreview> = mutableListOf()

        for (film in filmsList) {
            filmPreviews.add(
                FilmPreview(film.id, film.localizedName, null)
            )
        }

        view?.setFilmPreviewList(filmPreviews)
    }

    private fun generateFilmPreviewListBitmaps(filmList: List<Film>) {

        for (film in filmList) {
            if (film.imageUrl != null) {
                mainListModel.getFilmImageByUrl(
                    film.imageUrl,
                    { bitmap ->
                        view?.setFilmPreviewBitmap(film.id, bitmap)
                    },
                    { }
                )
            }
        }
    }
}