package com.dudencovgmail.splashes.presentation.view.base

import android.support.v7.widget.RecyclerView

abstract class BaseRecyclerAdapter<T : BaseViewHolder<*>> : RecyclerView.Adapter<T>() {

    init {
        this.setHasStableIds(true)
    }
}