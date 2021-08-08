package com.dudencovgmail.splashes.data.remote

import com.dudencovgmail.splashes.data.remote.dto.responses.UserPhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") key: String,
        @Query("page") currentPage: Int,
        @Query("per_page") perPage: Int
    ): ArrayList<UserPhotoResponse>
}