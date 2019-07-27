package com.dudencovgmail.splashes.presentation.notview.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.domain.IUseCases
import com.dudencovgmail.splashes.presentation.notview.base.APagingModel
import com.dudencovgmail.splashes.presentation.notview.base.IMainFragmentViewModel
import com.dudencovgmail.splashes.util.createConfig
import java.util.concurrent.Executors
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(interactor: IUseCases) : IMainFragmentViewModel, APagingModel(interactor) {

    override var pagedList: LiveData<PagedList<Model>>? = null
    override var progress: LiveData<Boolean> = MutableLiveData<Boolean>()
    override var errorMessage: LiveData<String> = MutableLiveData<String>()

    init {
        pagedList = LivePagedListBuilder(getDataSourceFactory(), createConfig())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()

        showProgress(progress)
        showErrorMessage(errorMessage)
    }

    override fun onCleared() {
        super.onCleared()
        clear()
    }
}



