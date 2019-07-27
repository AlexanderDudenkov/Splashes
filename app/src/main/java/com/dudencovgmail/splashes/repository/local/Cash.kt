package com.dudencovgmail.splashes.repository.local

import com.dudencovgmail.splashes.data.Model

class Cash :IDb{
    private var modelList = ArrayList<Model>()

    override fun readModelList() = modelList

    override fun writeModel(model: Model) {
        modelList.add(model)
    }

    override fun writeModelList(list: ArrayList<Model>) {
        modelList.addAll(list)
    }
}
