package com.dudencovgmail.splashes.presentation.notview.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.domain.UseCases
import com.dudencovgmail.splashes.presentation.notview.base.AMainFragmentViewModel
import com.dudencovgmail.splashes.presentation.notview.paging.DataSourceFactory
import com.dudencovgmail.splashes.presentation.notview.paging.GalleryDataSource
import com.dudencovgmail.splashes.repository.remote.Repository
import com.github.ajalt.timberkt.Timber.d
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors

class MainFragmentViewModel(interactor: UseCases) : AMainFragmentViewModel() {

    private var compositeDisposable = CompositeDisposable()
    override var pagedList: LiveData<PagedList<Model>>? = null
    override var progress: LiveData<Boolean> = MutableLiveData<Boolean>()
    override var error: LiveData<String> = MutableLiveData<String>()

    init {
        val dataSourceFactory = DataSourceFactory(interactor, compositeDisposable)

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPrefetchDistance(3)
                .setPageSize(10).build()

        pagedList = LivePagedListBuilder(dataSourceFactory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
        Repository.getPagingList = pagedList//todo temp decision

        progress = Transformations.switchMap(dataSourceFactory.dataSource, GalleryDataSource::progress)
        error = Transformations.switchMap(dataSourceFactory.dataSource, GalleryDataSource::error)
    }

    override fun onCleared() {
        super.onCleared()
        d { "onCleared()" }
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }
}



