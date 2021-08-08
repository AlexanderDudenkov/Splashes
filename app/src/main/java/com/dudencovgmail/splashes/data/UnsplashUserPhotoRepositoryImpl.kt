package com.dudencovgmail.splashes.data

import androidx.datastore.core.DataStore
import com.dudencovgmail.splashes.data.local.dto.UserPhoto
import com.dudencovgmail.splashes.data.remote.UnsplashApiService
import com.dudencovgmail.splashes.data.remote.dto.responses.UserPhotoResponse
import com.dudencovgmail.splashes.data.remote.utils.map
import com.dudencovgmail.splashes.domain.UnsplashUserPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UnsplashUserPhotoRepositoryImpl @Inject constructor(
    private val networkManager: NetworkManager,
    private val unsplashApiService: UnsplashApiService,
    private val dataStore: DataStore<ArrayList<UserPhoto>>
) : UnsplashUserPhotoRepository {

    override fun getPhotos(key: String, currentPage: Int, perPage: Int): Flow<List<UserPhoto>> =
        if (!networkManager.isDefaultNetworkActive) {
            flow {
                emit(unsplashApiService.getPhotos(key, currentPage, perPage))
            }.map { list: java.util.ArrayList<UserPhotoResponse> -> list.map { it.map() } as ArrayList<UserPhoto> }
                .mapNotNull { list: ArrayList<UserPhoto> -> dataStore.updateData { list } }
                .onEmpty { dataStore.data }
        } else {
            dataStore.data
        }.flowOn(Dispatchers.IO)
            .conflate()
}