package com.dudencovgmail.splashes.data.remote.utils

import com.dudencovgmail.splashes.data.local.dto.User
import com.dudencovgmail.splashes.data.remote.dto.responses.UserResponse

fun UserResponse.map() = User(name ?: "", location ?: "")