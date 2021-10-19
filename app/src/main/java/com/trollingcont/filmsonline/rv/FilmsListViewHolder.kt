package com.trollingcont.filmsonline.rv

import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemFilmsListBinding

class FilmsListViewHolder(
    binding: ItemFilmsListBinding
) : RecyclerView.ViewHolder(
    binding.root
) {
    private val itemBinding = binding

    fun bind(
        filmsListAdapter: FilmsListAdapter,
        layoutManager: RecyclerView.LayoutManager
    ) {
        itemBinding.filmsListRecyclerView.adapter = filmsListAdapter
        itemBinding.filmsListRecyclerView.layoutManager = layoutManager
    }
}