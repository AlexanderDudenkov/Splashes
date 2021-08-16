package com.dudencovgmail.splashes.data

import androidx.datastore.core.DataStore
import com.dudencovgmail.splashes.data.local.dto.UserPhoto
import com.dudencovgmail.splashes.data.remote.UnsplashApiService
import com.dudencovgmail.splashes.data.remote.utils.map
import com.dudencovgmail.splashes.domain.UnsplashUserPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

class UnsplashUserPhotoRepositoryImpl @Inject constructor(
    private val unsplashApiService: UnsplashApiService,
    private val dataStore: DataStore<List<UserPhoto>>
) : UnsplashUserPhotoRepository {

    override fun getPhotos(key: String, currentPage: Int, perPage: Int): Flow<List<UserPhoto>> =
        flow {
            val res = unsplashApiService.getPhotos(key, currentPage, perPage)

            if (res.isNotEmpty()) {
                emit(dataStore.updateData { res.map { it.map() } })
            } else {
                emitAll(dataStore.data)
            }
        }
            .catch { if (it is IOException) emitAll(dataStore.data) else throw it }
            .flowOn(Dispatchers.IO)
            .conflate()
}