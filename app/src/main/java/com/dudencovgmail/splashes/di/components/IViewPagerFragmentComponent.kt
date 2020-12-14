package com.dudencovgmail.splashes.di.components

import com.dudencovgmail.splashes.di.modules.ViewPagerFragmentModule
import com.dudencovgmail.splashes.di.scopes.FragmentScope
import com.dudencovgmail.splashes.presentation.view.fragments.ViewPagerFragment
import dagger.Component

@FragmentScope
@Component(modules = [ViewPagerFragmentModule::class], dependencies = [AppComponent::class])
interface IViewPagerFragmentComponent {
    fun inject(fragment: ViewPagerFragment)
}