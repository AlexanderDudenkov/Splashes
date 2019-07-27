package com.dudencovgmail.splashes.repository.local

import com.dudencovgmail.splashes.data.Model
import javax.inject.Inject

class LocalRepo : ILocalRepo {

    @Inject
    override lateinit var db: IDb

    override fun writeModel(model: Model) {
        db.writeModel(model)
    }

    override fun writeModelList(list: ArrayList<Model>) {
        db.writeModelList(list)
    }

    override fun readModelList() = db.readModelList()
}