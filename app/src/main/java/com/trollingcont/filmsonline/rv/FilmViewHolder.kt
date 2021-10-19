package com.trollingcont.filmsonline.rv

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemFilmBinding

class FilmViewHolder(
    binding: ItemFilmBinding,
    private val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(
    binding.root
) {

    private val itemBinding = binding

    fun bind(itemContent: Pair<String, Bitmap?>) {
        itemBinding.filmName.text = itemContent.first
        itemBinding.filmImage.setImageBitmap(itemContent.second)

        itemBinding.root.setOnClickListener {
            onClick(itemContent.first)
        }
    }
}