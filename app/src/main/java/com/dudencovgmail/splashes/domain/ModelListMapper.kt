package com.dudencovgmail.splashes.domain

import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.data.PhotosUrl
import com.dudencovgmail.splashes.data.User
import com.dudencovgmail.splashes.repository.remote.models.response.ModelResponse
import com.dudencovgmail.splashes.repository.remote.models.response.PhotosUrlResponse
import com.dudencovgmail.splashes.repository.remote.models.response.UserResponse

class ModelListMapper : IModelListMapper {

    override fun getModelList(modelList: ArrayList<Model>, list: ArrayList<ModelResponse>): ArrayList<Model> {
        for (i in 0 until list.size) {
            var model: Model?
            if (modelList.size < list.size) {
                model = Model()
            } else {
                model = modelList[i]
            }
            modelList.add(modelMapper(model, list[i]))
        }
        return modelList
    }

    private fun modelMapper(model: Model, modelResponse: ModelResponse): Model =
            model.apply {
                modelResponse.idPhoto?.let {
                    if (this.idPhoto != modelResponse.idPhoto) this.idPhoto = modelResponse.idPhoto
                }
                modelResponse.photosUrl?.let {
                    this.photosUrl = photosUrlMapper(this.photosUrl, modelResponse.photosUrl)
                }
                modelResponse.user?.let {
                    this.user = userMapper(this.user, modelResponse.user)
                }
            }

    private fun photosUrlMapper(photosUrl: PhotosUrl?, photosUrlResponse: PhotosUrlResponse?): PhotosUrl =
            photosUrl ?: PhotosUrl().apply {
                photosUrlResponse?.urlLarge?.let {
                    if (this.urlLarge != photosUrlResponse.urlLarge) this.urlLarge = photosUrlResponse.urlLarge
                }
                photosUrlResponse?.urlMedium?.let {
                    if (this.urlMedium != photosUrlResponse.urlMedium) this.urlMedium = photosUrlResponse.urlMedium
                }
                photosUrlResponse?.urlSmall?.let {
                    if (this.urlSmall != photosUrlResponse.urlSmall) this.urlSmall = photosUrlResponse.urlSmall
                }
                photosUrlResponse?.urlThumb?.let {
                    if (this.urlThumb != photosUrlResponse.urlThumb) this.urlThumb = photosUrlResponse.urlThumb
                }
            }

    private fun userMapper(user: User?, userResponse: UserResponse?): User =
            user ?: User().apply {
                userResponse?.location?.let {
                    if (this.location != userResponse.location) this.location = userResponse.location
                }
                userResponse?.name?.let {
                    if (this.name != userResponse.name) this.name = userResponse.name
                }
            }

}