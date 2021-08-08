package com.dudencovgmail.splashes.data.local.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T> typeToken(): Type = object : TypeToken<T>() {}.type

inline fun <reified T> Gson.fromJsonExt(json: String, defaultValue: T): T =
    if (json.isNotBlank()) fromJson(json, typeToken<T>()) else defaultValue