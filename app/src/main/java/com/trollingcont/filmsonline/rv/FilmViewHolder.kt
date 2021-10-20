package com.trollingcont.filmsonline.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemFilmBinding
import com.trollingcont.filmsonline.model.FilmPreview

class FilmViewHolder(
    binding: ItemFilmBinding,
    private val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(
    binding.root
) {

    private val itemBinding = binding
    private var filmId: Int = 0

    fun bind(itemContent: FilmPreview) {
        itemBinding.filmName.text = itemContent.name
        filmId = itemContent.id

        if (itemContent.bitmap != null) {
            itemBinding.noImageText.visibility = View.GONE
            itemBinding.filmImage.setImageBitmap(itemContent.bitmap)
        }

        itemBinding.root.setOnClickListener {
            onClick(filmId)
        }
    }
}