package com.trollingcont.filmsonline.rv

import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemGenreBinding

class GenreViewHolder(
    binding: ItemGenreBinding,
    private val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(
    binding.root
) {
    private val itemBinding = binding

    fun bind(genreName: String) {
        itemBinding.genreName.text = genreName

        itemBinding.root.setOnClickListener {
            onClick(genreName)
        }
    }
}