package com.dudencovgmail.splashes.data.dto.responses

import com.google.gson.annotations.SerializedName

class UserPhotoUrls {
    @SerializedName("full")
    val urlLarge: String? = null

    @SerializedName("regular")
    var urlMedium: String? = null

    @SerializedName("small")
    val urlSmall: String? = null

    @SerializedName("thumb")
    val urlThumb: String? = null
}