package com.trollingcont.filmsonline.rv

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemFilmBinding

class FilmsListAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<FilmViewHolder>() {

    var filmsList: List<Pair<String, Bitmap?>> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return FilmViewHolder(binding) {
            clickedFilmName -> onClick(clickedFilmName)
        }
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(filmsList[position])
    }

    override fun getItemCount() =
        filmsList.size
}