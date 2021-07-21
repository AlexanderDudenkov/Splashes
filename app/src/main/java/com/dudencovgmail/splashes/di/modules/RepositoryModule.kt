package com.dudencovgmail.splashes.di.modules

import com.dudencovgmail.splashes.domain.UnsplashUserPhotoRepository
import com.dudencovgmail.splashes.data.UnsplashUserPhotoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUnsplashUserPhotoRepository(value: UnsplashUserPhotoRepositoryImpl): UnsplashUserPhotoRepository
}