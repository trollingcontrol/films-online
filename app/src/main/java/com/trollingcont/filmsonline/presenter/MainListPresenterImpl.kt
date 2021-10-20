package com.trollingcont.filmsonline.presenter

import com.trollingcont.filmsonline.contract.MainListContract
import com.trollingcont.filmsonline.model.Film
import com.trollingcont.filmsonline.model.FilmPreview
import com.trollingcont.filmsonline.model.FilmPreviewComparator
import javax.inject.Inject

class MainListPresenterImpl @Inject constructor(
    private val model: MainListContract.Model
) : MainListContract.Presenter {

    private var view: MainListContract.View? = null
    private var highlightedGenre: String? = null
    private val comparator = FilmPreviewComparator()

    override fun attach(view: MainListContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun loadMainList() {

        val selectedGenre = model.getSelectedGenre()

        model.getFilms(
            { filmsList ->
                initializeMainList(filmsList, selectedGenre)
            },
            { }
        )
    }

    override fun selectGenre(genreName: String) {
        model.getFilms(
            { filmsList ->
                val filmsByGenre = getFilmsByGenre(filmsList, genreName)
                updateFilmPreviewList(filmsByGenre)

                if (highlightedGenre != null) {
                    view?.removeHighlightFromGenre(highlightedGenre!!)
                }

                highlightedGenre = genreName

                if (model.getSelectedGenre() != genreName) {
                    model.setSelectedGenre(genreName)
                }

                view?.highlightGenre(genreName)
            },
            { }
        )
    }

    private fun initializeMainList(filmsList: List<Film>, selectedGenre: String?) {
        val genresList = getGenres(filmsList)

        generateGenresList(genresList)

        if (selectedGenre != null) {
            selectGenre(selectedGenre)
        } else {
            updateFilmPreviewList(filmsList)
        }
    }

    private fun getGenres(films: List<Film>): List<String> {

        val genresList: MutableList<String> = mutableListOf()

        for (film in films) {
            if (film.genres != null) {
                for (genre in film.genres) {
                    if (!genresList.contains(genre)) {
                        genresList.add(genre)
                    }
                }
            }
        }

        return genresList
    }

    private fun getFilmsByGenre(filmsList: List<Film>, genreName: String): List<Film> {

        val filmsByGenre: MutableList<Film> = mutableListOf()

        for (film in filmsList) {
            if (film.genres != null && film.genres.contains(genreName)) {
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
            if (film.id != null) {
                filmPreviews.add(
                    FilmPreview(film.id, film.localizedName, null)
                )
            }
        }

        view?.setFilmPreviewList(filmPreviews.sortedWith(comparator))
    }

    private fun generateFilmPreviewListBitmaps(filmList: List<Film>) {

        for (film in filmList) {
            if (film.imageUrl != null && film.id != null) {
                model.getFilmImageByUrl(
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