package com.dudencovgmail.splashes.domain

import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.data.PhotosUrl
import com.dudencovgmail.splashes.data.User
import com.dudencovgmail.splashes.repository.remote.models.response.ModelResponse
import com.dudencovgmail.splashes.repository.remote.models.response.PhotosUrlResponse
import com.dudencovgmail.splashes.repository.remote.models.response.UserResponse

class ModelListBuilder : IModelListBuilder {

    override fun getModelList(list: ArrayList<ModelResponse>): ArrayList<Model> {
        val modelList = ArrayList<Model>()
        for (i in 0 until list.size) {
            modelList.add(modelMapper(list[i]))
        }
        return modelList
    }

    private fun modelMapper(modelResponse: ModelResponse): Model =
            Model().apply {
                modelResponse.idPhoto?.let { this.idPhoto = modelResponse.idPhoto }
                modelResponse.photosUrl?.let { this.photosUrl = photosUrlMapper(modelResponse.photosUrl) }
                modelResponse.user?.let { this.user = userMapper(modelResponse.user) }
            }

    private fun photosUrlMapper(photosUrlResponse: PhotosUrlResponse?): PhotosUrl =
            PhotosUrl().apply {
                photosUrlResponse?.urlLarge?.let { this.urlLarge = photosUrlResponse.urlLarge }
                photosUrlResponse?.urlMedium?.let { this.urlMedium = photosUrlResponse.urlMedium }
                photosUrlResponse?.urlSmall?.let { this.urlSmall = photosUrlResponse.urlSmall }
                photosUrlResponse?.urlThumb?.let { this.urlThumb = photosUrlResponse.urlThumb }
            }

    private fun userMapper(userResponse: UserResponse?): User =
            User().apply {
                userResponse?.location?.let { this.location = userResponse.location }
                userResponse?.name?.let { this.name = userResponse.name }
            }

}