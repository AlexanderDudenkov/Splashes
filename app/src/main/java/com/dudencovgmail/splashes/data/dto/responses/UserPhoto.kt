package com.dudencovgmail.splashes.data.dto.responses

import com.google.gson.annotations.SerializedName

class UserPhoto(
    @SerializedName("id")
    val idPhoto: String? = null,

    @SerializedName("urls")
    val photosUrls: UserPhotoUrls? = null,

    @SerializedName("user")
    val user: User? = null
)