package com.dudencovgmail.splashes.data.local.dto

data class UserPhotoUrls(
    val urlLarge: String,
    val urlMedium: String,
    val urlSmall: String,
    val urlThumb: String
) {
    companion object {
        val default = UserPhotoUrls("", "", "", "")
    }
}