package com.dudencovgmail.splashes.di.modules

import android.content.Context
import android.support.v7.util.DiffUtil
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.di.scopes.FragmentScope
import com.dudencovgmail.splashes.presentation.view.activities.MainActivity
import com.dudencovgmail.splashes.presentation.view.adapters.GalleryAdapter2
import com.dudencovgmail.splashes.util.SimpleItemDiffCallback
import dagger.Module
import dagger.Provides

@Module
class MainFragmentModule(private val activity: MainActivity) {

    @Provides
    @FragmentScope
    fun provideFragmentModule(): Context = activity.baseContext

    @Provides
    @FragmentScope
    fun provideGalleryAdapter(itemDiffCallback: DiffUtil.ItemCallback<Model>): GalleryAdapter2 =
            GalleryAdapter2(itemDiffCallback)

    @Provides
    @FragmentScope
    fun provideItemCallback(): DiffUtil.ItemCallback<Model> = SimpleItemDiffCallback()
}