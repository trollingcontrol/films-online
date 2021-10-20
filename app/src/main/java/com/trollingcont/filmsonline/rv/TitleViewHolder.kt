package com.trollingcont.filmsonline.rv

import androidx.recyclerview.widget.RecyclerView
import com.trollingcont.filmsonline.databinding.ItemTitleBinding

class TitleViewHolder(
    binding: ItemTitleBinding
) : RecyclerView.ViewHolder(
    binding.root
) {
    private val itemBinding = binding

    fun bind(itemContent: String) {
        itemBinding.titleName.text = itemContent
    }
}