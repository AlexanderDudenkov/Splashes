package com.dudencovgmail.splashes.di.modules

import com.dudencovgmail.splashes.domain.UnsplashUserPhotoUseCase
import com.dudencovgmail.splashes.domain.UnsplashUserPhotoUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindUnsplashUserPhotoUseCase(value: UnsplashUserPhotoUseCaseImpl): UnsplashUserPhotoUseCase
}