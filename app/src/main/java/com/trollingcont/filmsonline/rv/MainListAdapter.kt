package com.trollingcont.filmsonline.rv

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemFilmsListBinding
import com.trollingcont.filmsonline.databinding.ItemGenreBinding
import com.trollingcont.filmsonline.databinding.ItemTitleBinding
import com.trollingcont.filmsonline.model.FilmPreview
import com.trollingcont.filmsonline.model.MainListItem

class MainListAdapter(
    private val onClick: (Int, String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_TITLE = 0
        const val ITEM_TYPE_GENRE = 1
        const val ITEM_TYPE_FILMS = 2
    }

    private val filmsListAdapter = FilmsListAdapter { clickedFilmName ->
        onClick(ITEM_TYPE_FILMS, clickedFilmName)
    }

    private var genresList: List<String> = emptyList()

    fun setGenresList(genres: List<String>) {
        this.genresList = genres
        notifyDataSetChanged()
    }

    fun setFilmsList(filmPreviews: List<FilmPreview>) {
        filmsListAdapter.filmsList = filmPreviews.toMutableList()
        notifyDataSetChanged()
    }

    fun setFilmBitmap(filmId: Int, bitmap: Bitmap) {
        for ((index, filmPreview) in filmsListAdapter.filmsList.iterator().withIndex()) {
            if (filmPreview.id == filmId) {
                filmsListAdapter.filmsList[index] =
                    FilmPreview(filmId, filmPreview.name, bitmap)
                notifyItemChanged(index)

                return
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_TYPE_TITLE -> {
                val binding = ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TitleViewHolder(binding)
            }
            ITEM_TYPE_GENRE -> {
                val binding = ItemGenreBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                GenreViewHolder(binding) { clickedGenreName ->
                    onClick(ITEM_TYPE_GENRE, clickedGenreName)
                }
            }
            ITEM_TYPE_FILMS -> {
                val binding = ItemFilmsListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                FilmsListViewHolder(binding)
            }
            else -> throw IllegalStateException("Unknown view type $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> {
                (holder as TitleViewHolder).bind(MainListItem.Title("Жанры"))
            }
            genresList.size + 1 -> {
                (holder as TitleViewHolder).bind(MainListItem.Title("Фильмы"))
            }
            genresList.size + 2 -> {
                (holder as FilmsListViewHolder).bind(filmsListAdapter)
            }
            else -> {
                (holder as GenreViewHolder).bind(genresList[position - 1])
            }
        }
    }

    override fun getItemCount() =
        genresList.size + 3

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0, genresList.size + 1 -> ITEM_TYPE_TITLE
            genresList.size + 2 -> ITEM_TYPE_FILMS
            else -> ITEM_TYPE_GENRE
        }
}