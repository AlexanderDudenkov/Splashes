package com.dudencovgmail.splashes.presentation.notview.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.dudencovgmail.splashes.data.Model

abstract class AItemViewPagerFrVM : ViewModel() {
    abstract var pagedList: LiveData<PagedList<Model>>?
}