package com.dudencovgmail.splashes.repository.remote.models.response

import com.google.gson.annotations.SerializedName

class PhotosUrlResponse {

    @SerializedName("full")
    val urlLarge: String? = null

    @SerializedName("regular")
    val urlMedium: String? = null

    @SerializedName("small")
    val urlSmall: String? = null

    @SerializedName("thumb")
    val urlThumb: String? = null
}

