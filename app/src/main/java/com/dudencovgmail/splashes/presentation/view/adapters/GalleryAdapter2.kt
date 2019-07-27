package com.dudencovgmail.splashes.presentation.view.adapters

import android.arch.paging.PagedListAdapter
import android.databinding.BindingAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import android.widget.ImageView
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.databinding.GalleryItemBinding
import com.dudencovgmail.splashes.util.SimpleItemDiffCallback
import com.dudencovgmail.splashes.util.inflater
import com.dudencovgmail.splashes.util.loadImage

class GalleryAdapter2(itemDiffCallback: DiffUtil.ItemCallback<Model> = SimpleItemDiffCallback())
    : PagedListAdapter<Model, GalleryHolder>(itemDiffCallback) {

    private var clickedListener: ((Int) -> Unit)? = null
    private var binding: GalleryItemBinding? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GalleryHolder {
        binding = GalleryItemBinding.inflate(viewGroup.inflater(), viewGroup, false)
        return GalleryHolder(binding, clickedListener)
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    fun setOnClickListener(listener: (Int) -> Unit) {
        clickedListener = listener
    }

    companion object {
        @BindingAdapter("app:photosUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            imageView.loadImage(url)
        }
    }
}






