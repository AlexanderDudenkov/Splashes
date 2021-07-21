package com.dudencovgmail.splashes.data

import com.dudencovgmail.splashes.data.dto.responses.UserPhoto
import com.dudencovgmail.splashes.domain.UnsplashUserPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UnsplashUserPhotoRepositoryImpl @Inject constructor(
    private val unsplashApiService: UnsplashApiService
) : UnsplashUserPhotoRepository {

    override fun getPhotos(key: String, currentPage: Int, perPage: Int): Flow<List<UserPhoto>> {
        return flow {
            emit(unsplashApiService.getPhotos(key, currentPage, perPage))
        }.flowOn(Dispatchers.IO)
    }
}