package com.dudencovgmail.splashes.domain

import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.repository.remote.models.send.SendModel
import io.reactivex.Single

interface UseCases {
    fun getPhotos(page:Int, perPage:Int): Single<ArrayList<Model>>
}