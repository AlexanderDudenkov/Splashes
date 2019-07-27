package com.dudencovgmail.splashes.domain

import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.repository.IRepository
import com.dudencovgmail.splashes.repository.remote.models.send.SendModel
import com.dudencovgmail.splashes.util.Constants
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Interactor @Inject constructor(private val repository: IRepository, private val mapper: IModelListBuilder) : IUseCases {

    override fun getModelList() = repository.localRepo.readModelList()

    override fun getModelList(page: Int, perPage: Int): Single<ArrayList<Model>> =
            repository.remoteRepo.getPhotos(SendModel(Constants.API_KEY, page, perPage))
                    .subscribeOn(Schedulers.io())
                    .map { modelListResponse ->
                        mapper.getModelList(modelListResponse).also {
                            repository.localRepo.writeModelList(it)
                        }
                    }
                    .observeOn(AndroidSchedulers.mainThread())

}

