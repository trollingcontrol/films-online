package com.trollingcont.filmsonline.rv

import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemFilmBinding
import com.trollingcont.filmsonline.model.FilmPreview

class FilmViewHolder(
    binding: ItemFilmBinding,
    private val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(
    binding.root
) {

    private val itemBinding = binding

    fun bind(itemContent: FilmPreview) {
        itemBinding.filmName.text = itemContent.name

        if (itemContent.bitmap != null) {
            itemBinding.filmImage.setImageBitmap(itemContent.bitmap)
        }

        itemBinding.root.setOnClickListener {
            onClick(itemContent.name)
        }
    }
}