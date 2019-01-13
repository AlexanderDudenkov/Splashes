package com.dudencovgmail.splashes.presentation.adapters

import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.databinding.GalleryItemBinding
import com.dudencovgmail.splashes.presentation.base.BaseViewHolder

class GalleryHolder(private val binding: GalleryItemBinding?,
                    clickedListener: ((Int) -> Unit)? = null)
    : BaseViewHolder<Model>(binding!!.root, clickedListener) {

    override fun bindData(data: Model?) {
        binding?.model = data
    }
}