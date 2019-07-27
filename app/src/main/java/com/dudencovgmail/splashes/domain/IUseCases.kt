package com.dudencovgmail.splashes.domain

import com.dudencovgmail.splashes.data.Model
import io.reactivex.Single

interface IUseCases {
    fun getModelList(page: Int, perPage: Int): Single<ArrayList<Model>>
    fun getModelList(): ArrayList<Model>

}