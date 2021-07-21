package com.dudencovgmail.splashes.data

import com.dudencovgmail.splashes.data.dto.responses.UserPhoto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") key: String,
        @Query("page") currentPage: Int,
        @Query("per_page") perPage: Int
    ): List<UserPhoto>
}