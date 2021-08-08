package com.dudencovgmail.splashes.data.remote.utils

import com.dudencovgmail.splashes.data.local.dto.UserPhotoUrls
import com.dudencovgmail.splashes.data.remote.dto.responses.UserPhotoUrlsResponse

fun UserPhotoUrlsResponse.map() = UserPhotoUrls(
    urlLarge ?: "",
    urlMedium ?: "",
    urlSmall ?: "",
    urlThumb ?: ""
)