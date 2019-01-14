package com.dudencovgmail.splashes.presentation.notview.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.domain.UseCases
import com.dudencovgmail.splashes.presentation.notview.base.ADetailViewModel
import com.dudencovgmail.splashes.repository.remote.Repository

class DetailViewModel(interactor: UseCases) : ADetailViewModel() {

    override var pagedList: LiveData<PagedList<Model>>? = null

    init {
        pagedList = Repository.getPagingList//todo temp decision
    }

}



