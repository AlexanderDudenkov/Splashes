package com.dudencovgmail.splashes.domain

import com.dudencovgmail.splashes.data.dto.responses.UserPhoto
import kotlinx.coroutines.flow.Flow

interface UnsplashUserPhotoUseCase {
    fun getPhotos(key: String, currentPage: Int, perPage: Int): Flow<List<UserPhoto>>
}