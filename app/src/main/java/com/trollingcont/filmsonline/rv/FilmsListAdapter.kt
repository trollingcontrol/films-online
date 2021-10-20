package com.trollingcont.filmsonline.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemFilmBinding
import com.trollingcont.filmsonline.model.FilmPreview

class FilmsListAdapter(
    private val onClick: (Any) -> Unit
) : RecyclerView.Adapter<FilmViewHolder>() {

    var filmsList: MutableList<FilmPreview> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FilmViewHolder(binding) { clickedFilmName ->
            onClick(clickedFilmName)
        }
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(filmsList[position])
    }

    override fun getItemCount() =
        filmsList.size
}