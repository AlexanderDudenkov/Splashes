package com.dudencovgmail.splashes.presentation.notview.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.domain.IUseCases
import com.dudencovgmail.splashes.presentation.notview.base.APagingModel
import com.dudencovgmail.splashes.presentation.notview.base.IViewPagerFragmentViewModel
import com.dudencovgmail.splashes.util.createConfig
import java.util.concurrent.Executors
import javax.inject.Inject

class ViewPagerFragmentViewModel @Inject constructor(interactor: IUseCases) : IViewPagerFragmentViewModel, APagingModel(interactor) {

    override var pagedList: LiveData<PagedList<Model>>? = null

    init {
        pagedList = LivePagedListBuilder(getDataSourceFactory(), createConfig())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
    }

    override fun onCleared() {
        super.onCleared()
        clear()
    }
}


