package com.dudencovgmail.splashes.repository.remote

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.repository.local.Cash
import com.dudencovgmail.splashes.repository.local.ILocal
import com.dudencovgmail.splashes.repository.remote.models.response.ModelResponse
import com.dudencovgmail.splashes.repository.remote.models.send.SendModel
import io.reactivex.Single

object Repository : IRepository {
    private var db: ILocal? = null

    override fun setDB(db: ILocal) {
        this.db = db
    }

    override fun writeModel(model: Model) {
        db?.writeModel(model)
    }

    override fun writeModelList(list: ArrayList<Model>) {
        db?.writeModelList(list)
    }

    override fun readModelList() = db?.readModelList() ?: ArrayList()

    override fun getPhotos(sendModel: SendModel): Single<ArrayList<ModelResponse>> {
        return OkhttpTool.createHttpConnection().getPhotos(sendModel.clientId, sendModel.page, sendModel.perPage)
    }

    var getPagingList: LiveData<PagedList<Model>>? = null
}
