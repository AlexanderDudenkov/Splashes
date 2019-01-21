package com.dudencovgmail.splashes.presentation.notview.base

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.dudencovgmail.splashes.data.Model

interface IItemViewPagerFrVM {
    var pagedList: LiveData<PagedList<Model>>?
}