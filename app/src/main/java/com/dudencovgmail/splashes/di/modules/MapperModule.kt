package com.dudencovgmail.splashes.di.modules

import com.dudencovgmail.splashes.di.scopes.ApplicationScope
import com.dudencovgmail.splashes.domain.IModelListBuilder
import com.dudencovgmail.splashes.domain.ModelListBuilder
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    @ApplicationScope
    fun provideIModelListBuilder(): IModelListBuilder = ModelListBuilder()
}