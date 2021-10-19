package com.trollingcont.filmsonline.rv

import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemFilmsListBinding

class FilmsListViewHolder(
    binding: ItemFilmsListBinding
) : RecyclerView.ViewHolder(
    binding.root
) {
    private val itemBinding = binding

    fun bind(filmsListAdapter: FilmsListAdapter) {
        itemBinding.filmsListRecyclerView.adapter = filmsListAdapter
    }
}