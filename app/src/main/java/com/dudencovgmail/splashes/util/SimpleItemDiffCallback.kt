package com.dudencovgmail.splashes.util

import android.support.v7.util.DiffUtil

class SimpleItemDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}