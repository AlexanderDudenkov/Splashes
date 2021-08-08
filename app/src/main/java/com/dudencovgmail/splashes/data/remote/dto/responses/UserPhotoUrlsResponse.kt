package com.dudencovgmail.splashes.data.remote.dto.responses

import com.google.gson.annotations.SerializedName

class UserPhotoUrlsResponse {
    @SerializedName("full")
    val urlLarge: String? = null

    @SerializedName("regular")
    var urlMedium: String? = null

    @SerializedName("small")
    val urlSmall: String? = null

    @SerializedName("thumb")
    val urlThumb: String? = null
}