package com.dudencovgmail.splashes.domain

import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.repository.remote.IRepository
import com.dudencovgmail.splashes.repository.remote.models.send.SendModel
import com.dudencovgmail.splashes.util.Constants
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Interactor(val repository: IRepository, private val mapper: IModelListBuilder) : UseCases {

    override fun getModelList() = repository.readModelList()

    override fun getModelList(page: Int, perPage: Int): Single<ArrayList<Model>> =
            repository.getPhotos(SendModel(Constants.API_KEY, page, perPage))
                    .subscribeOn(Schedulers.io())
                    .map { modelListResponse ->
                        mapper.getModelList(modelListResponse).also {
                            repository.writeModelList(it)
                        }
                    }
                    .observeOn(AndroidSchedulers.mainThread())

}

