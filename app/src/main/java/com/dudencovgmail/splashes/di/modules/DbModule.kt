package com.dudencovgmail.splashes.di.modules

import com.dudencovgmail.splashes.di.scopes.ApplicationScope
import com.dudencovgmail.splashes.repository.local.Cash
import com.dudencovgmail.splashes.repository.local.IDb
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    @ApplicationScope
    fun provideIDb(): IDb = Cash()
}