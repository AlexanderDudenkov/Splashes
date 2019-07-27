package com.dudencovgmail.splashes.di.modules

import com.dudencovgmail.splashes.di.scopes.FragmentScope
import com.dudencovgmail.splashes.presentation.view.adapters.GalleryPagerAdapter
import com.dudencovgmail.splashes.presentation.view.fragments.ViewPagerFragment
import dagger.Module
import dagger.Provides

@Module
class ViewPagerFragmentModule(private val fragment: ViewPagerFragment) {

    @Provides
    @FragmentScope
    fun provideGalleryPagerAdapter(): GalleryPagerAdapter = GalleryPagerAdapter(fragment)
}