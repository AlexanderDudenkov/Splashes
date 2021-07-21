package com.dudencovgmail.splashes.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dudencovgmail.splashes.data.dto.responses.UserPhoto
import com.dudencovgmail.splashes.databinding.ItemBinding

class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding2: ItemBinding

    var list: List<UserPhoto> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return object :
            RecyclerView.ViewHolder(
                ItemBinding.inflate(LayoutInflater.from(parent.context))
                    .also { binding2 = it }.root
            ) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding2.url = list[position].photosUrls?.urlSmall
    }

    override fun getItemCount(): Int = list.size

}