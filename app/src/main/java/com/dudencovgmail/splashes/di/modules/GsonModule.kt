package com.dudencovgmail.splashes.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GsonModule {

    @Provides
    @Singleton
    fun provideGsonBuilder(): GsonBuilder = GsonBuilder()

    @Provides
    @Singleton
    fun provideGson(builder: GsonBuilder): Gson = builder.setLenient().create()
}