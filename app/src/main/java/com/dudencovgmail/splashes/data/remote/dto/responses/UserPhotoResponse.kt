package com.dudencovgmail.splashes.data.remote.dto.responses

import com.google.gson.annotations.SerializedName

class UserPhotoResponse(
    @SerializedName("id")
    val idPhoto: String? = null,

    @SerializedName("urls")
    val photosUrls: UserPhotoUrlsResponse? = null,

    @SerializedName("user")
    val user: UserResponse? = null
)


