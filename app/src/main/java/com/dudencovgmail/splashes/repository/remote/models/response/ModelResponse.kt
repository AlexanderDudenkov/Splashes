package com.dudencovgmail.splashes.repository.remote.models.response

import com.google.gson.annotations.SerializedName

class ModelResponse {

    @SerializedName("id")
    val idPhoto: String? = null

    @SerializedName("urls")
    val photosUrl: PhotosUrlResponse? = null

    @SerializedName("user")
    val user: UserResponse? = null
}
