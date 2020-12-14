package com.dudencovgmail.splashes.util

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList


fun ViewModel.createConfig(initialLoadSizeHint: Int = 10,
                           perPage: Int = 10,
                           prefetchDistance: Int = 3): PagedList.Config {

    return PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(initialLoadSizeHint)
            .setPrefetchDistance(prefetchDistance)
            .setPageSize(perPage).build()
}

fun <T> ViewModel.createDataSourceFactory(dataSource: DataSource<Int, T>): DataSource.Factory<Int, T> {
    return object : DataSource.Factory<Int, T>() {
        override fun create(): DataSource<Int, T> {
            return dataSource
        }
    }
}

fun <T> ViewModel.createPageKeyedDataSource(
        loadInitial: (params: PageKeyedDataSource.LoadInitialParams<Int>,
                      callback: PageKeyedDataSource.LoadInitialCallback<Int, T>) -> Unit,
        loadAfter: (params: PageKeyedDataSource.LoadParams<Int>,
                    callback: PageKeyedDataSource.LoadCallback<Int, T>) -> Unit,
        loadBefore: ((params: PageKeyedDataSource.LoadParams<Int>,
                      callback: PageKeyedDataSource.LoadCallback<Int, T>) -> Unit)? = null)
        : DataSource<Int, T> {

    return object : PageKeyedDataSource<Int, T>() {
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
            loadInitial.invoke(params, callback)
        }

        @SuppressLint("CheckResult")
        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
            loadAfter.invoke(params, callback)
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
            loadBefore?.invoke(params, callback)
        }
    }
}

fun <T> ViewModel.getPageToLoad(result: ArrayList<T>, perPage: Int): Int {
    if (!result.isEmpty() && result.size % perPage == 0) {
        return (result.size / perPage) + 1
    }
    return 1
}

