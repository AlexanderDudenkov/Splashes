package com.dudencovgmail.splashes.presentation.utils

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("src")
fun ImageView.loadImage(url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Picasso.get()
            .load(url)
            .noPlaceholder()
            .into(this)
    }
}