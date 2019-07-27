package com.dudencovgmail.splashes.repository.remote

import com.dudencovgmail.splashes.repository.remote.models.response.ModelResponse
import com.dudencovgmail.splashes.repository.remote.models.send.SendModel
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepo : IRemoteRepo {
    @Inject
    override lateinit var apiService: IApiService

    override fun getPhotos(sendModel: SendModel): Single<ArrayList<ModelResponse>> {
        return apiService.getPhotos(sendModel.clientId, sendModel.page, sendModel.perPage)
    }
}