package com.dudencovgmail.splashes.presentation.adapters

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.domain.UseCases
import io.reactivex.disposables.CompositeDisposable

class DataSourceFactory(val interactor: UseCases,
                        val compositeDisposable: CompositeDisposable) : DataSource.Factory<Int, Model>() {

    var dataSource = MutableLiveData<GalleryDataSource>()

    override fun create(): DataSource<Int, Model> {
        val dataSource = GalleryDataSource(interactor, compositeDisposable)
        this.dataSource.postValue(dataSource)
        return dataSource
    }
}