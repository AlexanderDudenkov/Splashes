package com.dudencovgmail.splashes.presentation.notview.paging

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.domain.UseCases
import io.reactivex.disposables.CompositeDisposable

class GalleryDataSource(private val interactor: UseCases,
                        private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, Model>() {

    private var page = 1
    var progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Model>) {
        interactor.getPhotos(page, params.requestedLoadSize)
                .doOnSubscribe { disposable ->
                    compositeDisposable.add(disposable)
                    progress.postValue(true)
                }
                .subscribe({ result ->
                    progress.postValue(false)
                    page++
                    callback.onResult(result, null, page)
                }, { throwable ->
                    progress.postValue(false)
                    error.postValue(throwable.message)
                })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Model>) {
        interactor.getPhotos(page, params.requestedLoadSize)
                .doOnSubscribe { disposable ->
                    compositeDisposable.add(disposable)
                    progress.postValue(true)
                }
                .subscribe({ result ->
                    progress.postValue(false)
                    page++
                    callback.onResult(result, params.key + 1)
                }, { throwable ->
                    progress.postValue(false)
                    error.postValue(throwable.message)
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Model>) {

    }
}
