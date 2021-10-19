package com.trollingcont.filmsonline.rv

import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemTitleBinding
import com.trollingcont.filmsonline.model.MainListItem

class TitleViewHolder(
    binding: ItemTitleBinding
) : RecyclerView.ViewHolder(
    binding.root
) {
    private val itemBinding = binding

    fun bind(itemContent: MainListItem.Title) {
        itemBinding.titleName.text = itemContent.titleName
    }
}