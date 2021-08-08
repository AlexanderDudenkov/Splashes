package com.dudencovgmail.splashes.data.remote.utils

import com.dudencovgmail.splashes.data.local.dto.User
import com.dudencovgmail.splashes.data.local.dto.UserPhoto
import com.dudencovgmail.splashes.data.local.dto.UserPhotoUrls
import com.dudencovgmail.splashes.data.remote.dto.responses.UserPhotoResponse

fun UserPhotoResponse.map() = UserPhoto(
    idPhoto ?: "",
    photosUrls?.map() ?: UserPhotoUrls.default,
    user?.map() ?: User.default
)