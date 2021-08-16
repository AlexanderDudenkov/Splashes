package com.dudencovgmail.splashes.data.local.utils.json_serializers

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.dudencovgmail.splashes.data.local.dto.UserPhoto
import com.dudencovgmail.splashes.data.local.utils.fromJsonExt
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.InputStream
import java.io.OutputStream

val Context.userPhotos by dataStore("userPhotosDataStore", UserPhotosJsonSerializer())

class UserPhotosJsonSerializer(
    val gson: Gson = Gson(),
    override val defaultValue: List<UserPhoto> = emptyList(),
) : Serializer<List<UserPhoto>> {

    override suspend fun readFrom(input: InputStream): List<UserPhoto> {
        try {
            val json: String = input.readBytes().decodeToString()
            return gson.fromJsonExt(json, defaultValue)
        } catch (e: JsonSyntaxException) {
            throw CorruptionException("Cannot read stored data", e)
        }
    }

    override suspend fun writeTo(t: List<UserPhoto>, output: OutputStream) {
        val bytes = gson.toJson(t).encodeToByteArray()
        kotlin.runCatching {
            output.write(bytes)
        }
    }
}
