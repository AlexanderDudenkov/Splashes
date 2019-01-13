package com.dudencovgmail.splashes.data

import com.dudencovgmail.splashes.repository.remote.models.response.PhotosUrlResponse
import com.dudencovgmail.splashes.repository.remote.models.response.UserResponse

class Model(
        var idPhoto: String? = null,
        var photosUrl: PhotosUrl? = null,
        var user: User? = null)