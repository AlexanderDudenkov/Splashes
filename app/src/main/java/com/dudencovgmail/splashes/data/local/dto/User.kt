package com.dudencovgmail.splashes.data.local.dto

data class User(
    val name: String,
    val location: String
) {
    companion object {
        val default = User("", "")
    }
}