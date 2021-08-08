package com.dudencovgmail.splashes.data.local.dto

data class UserPhoto(
    val idPhoto: String,
    val photosUrls: UserPhotoUrls,
    val user: User
) {
    companion object {
        val default =
            UserPhoto("", UserPhotoUrls.default, User.default)
    }
}