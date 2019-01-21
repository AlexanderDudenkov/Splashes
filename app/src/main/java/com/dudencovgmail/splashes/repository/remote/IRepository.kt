package com.dudencovgmail.splashes.repository.remote

import com.dudencovgmail.splashes.repository.local.ILocal
import com.dudencovgmail.splashes.repository.remote.models.response.ModelResponse
import com.dudencovgmail.splashes.repository.remote.models.send.SendModel
import io.reactivex.Single

interface IRepository : ILocal {
    fun setDB(db: ILocal)

    fun getPhotos(sendModel: SendModel): Single<ArrayList<ModelResponse>>
}
