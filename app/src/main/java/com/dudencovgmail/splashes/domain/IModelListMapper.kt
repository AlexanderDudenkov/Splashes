package com.dudencovgmail.splashes.domain

import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.repository.remote.models.response.ModelResponse

interface IModelListMapper {
    fun getModelList(modelList: ArrayList<Model>, list: ArrayList<ModelResponse>): ArrayList<Model>
}