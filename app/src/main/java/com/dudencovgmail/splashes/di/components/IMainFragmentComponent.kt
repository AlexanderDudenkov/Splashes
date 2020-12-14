package com.dudencovgmail.splashes.di.components

import com.dudencovgmail.splashes.di.modules.MainFragmentModule
import com.dudencovgmail.splashes.di.scopes.FragmentScope
import com.dudencovgmail.splashes.presentation.view.fragments.MainFragment
import dagger.Component

@FragmentScope
@Component(modules = [MainFragmentModule::class], dependencies = [AppComponent::class])
interface IMainFragmentComponent {
    fun inject(mainFragment: MainFragment)
}