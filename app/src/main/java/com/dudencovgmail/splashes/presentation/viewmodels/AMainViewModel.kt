package com.dudencovgmail.splashes.presentation.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.dudencovgmail.splashes.data.Model

abstract class AMainViewModel : ViewModel() {

    abstract var pagedList: LiveData<PagedList<Model>>?
    abstract var progress: LiveData<Boolean>
    abstract var error: LiveData<String>
}