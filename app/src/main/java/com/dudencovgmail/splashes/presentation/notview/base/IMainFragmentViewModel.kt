package com.dudencovgmail.splashes.presentation.notview.base

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.dudencovgmail.splashes.data.Model

interface IMainFragmentViewModel {
    var pagedList: LiveData<PagedList<Model>>?
    var progress: LiveData<Boolean>
    var errorMessage: LiveData<String>
}