package com.dudencovgmail.splashes.domain

import com.dudencovgmail.splashes.data.local.dto.UserPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnsplashUserPhotoUseCaseImpl @Inject constructor(
        private val unsplashUserPhotoRepository: UnsplashUserPhotoRepository
) : UnsplashUserPhotoUseCase {

    override fun getPhotos(key: String, currentPage: Int, perPage: Int): Flow<List<UserPhoto>> {
        return unsplashUserPhotoRepository.getPhotos(key, currentPage, perPage)
    }
}