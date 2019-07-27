package com.dudencovgmail.splashes.presentation.notview.base

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.domain.IUseCases
import com.dudencovgmail.splashes.util.createDataSourceFactory
import com.dudencovgmail.splashes.util.createPageKeyedDataSource
import com.dudencovgmail.splashes.util.getPageToLoad
import io.reactivex.disposables.CompositeDisposable

abstract class APagingModel(val interactor: IUseCases) : ViewModel() {

    private var perPage = 10
    private var compositeDisposable = CompositeDisposable()
    private var progress: LiveData<Boolean>? = null
    private var error: LiveData<String>? = null

    fun showProgress(_progress: LiveData<Boolean>) {
        progress = _progress
    }

    fun showErrorMessage(_error: LiveData<String>) {
        error = _error
    }

    fun clear() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }

    fun getDataSourceFactory(): DataSource.Factory<Int, Model> {
        val dataSource = createPageKeyedDataSource(getLoadInitial(), getLoadAfter())
        return createDataSourceFactory(dataSource)
    }

    private fun getLoadInitial(): (params: PageKeyedDataSource.LoadInitialParams<Int>,
                                   callback: PageKeyedDataSource.LoadInitialCallback<Int, Model>) -> Unit {

        val result = interactor.getModelList()

        if (!result.isEmpty()) {
            return { params: PageKeyedDataSource.LoadInitialParams<Int>,
                     callback: PageKeyedDataSource.LoadInitialCallback<Int, Model> ->
                callback.onResult(result, null,
                        getPageToLoad(interactor.getModelList(), perPage))
            }
        } else {
            return { params: PageKeyedDataSource.LoadInitialParams<Int>,
                     callback: PageKeyedDataSource.LoadInitialCallback<Int, Model> ->
                getResult { result ->
                    callback.onResult(result, null,
                            getPageToLoad(interactor.getModelList(), perPage))
                }
            }
        }
    }

    private fun getLoadAfter(): (params: PageKeyedDataSource.LoadParams<Int>,
                                 callback: PageKeyedDataSource.LoadCallback<Int, Model>) -> Unit {

        return { params: PageKeyedDataSource.LoadParams<Int>,
                 callback: PageKeyedDataSource.LoadCallback<Int, Model> ->
            getResult { result -> callback.onResult(result, params.key + 1) }
        }
    }

    @SuppressLint("CheckResult")
    private fun getResult(resultCallback: (ArrayList<Model>) -> Unit) {
        interactor.getModelList(getPageToLoad(interactor.getModelList(), perPage), perPage)
                .doOnSubscribe { disposable ->
                    compositeDisposable.add(disposable)
                    progress?.let { (progress as MutableLiveData<Boolean>).postValue(true) }
                }
                ?.subscribe({ result ->
                    progress?.let { (progress as MutableLiveData<Boolean>).postValue(false) }
                    resultCallback.invoke(result)
                }, { throwable ->
                    progress?.let { (progress as MutableLiveData<Boolean>).postValue(false) }
                    error?.let { (error as MutableLiveData<String>).postValue(throwable.message) }
                })
    }
}