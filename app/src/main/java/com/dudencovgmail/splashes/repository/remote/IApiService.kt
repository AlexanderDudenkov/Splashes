package com.dudencovgmail.splashes.repository.remote

import com.dudencovgmail.splashes.repository.remote.models.response.ModelResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET("photos")
    fun getPhotos(@Query("client_id") key: String,
                  @Query("page") currentPage: Int,
                  @Query("per_page") perPage: Int): Single<ArrayList<ModelResponse>>

}
