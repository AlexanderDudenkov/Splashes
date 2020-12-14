package com.dudencovgmail.splashes.di.components

import android.content.Context
import com.dudencovgmail.splashes.di.modules.AppModule
import com.dudencovgmail.splashes.di.scopes.ApplicationScope
import com.dudencovgmail.splashes.presentation.notview.viewmodels.MainFragmentViewModel
import com.dudencovgmail.splashes.presentation.notview.viewmodels.ViewModelFactory
import com.dudencovgmail.splashes.presentation.notview.viewmodels.ViewPagerFragmentViewModel
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    fun mainViewModelFactory(): ViewModelFactory<MainFragmentViewModel>
    fun viewPagerViewModelFactory(): ViewModelFactory<ViewPagerFragmentViewModel>
}