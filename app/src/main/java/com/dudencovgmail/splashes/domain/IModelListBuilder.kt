package com.dudencovgmail.splashes.domain

import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.repository.remote.models.response.ModelResponse

interface IModelListBuilder {
    fun getModelList(list: ArrayList<ModelResponse>): ArrayList<Model>
}