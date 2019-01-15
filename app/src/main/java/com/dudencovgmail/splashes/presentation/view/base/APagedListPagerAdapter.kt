package com.dudencovgmail.splashes.presentation.view.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.paging.PagedList
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager

abstract class APagedListPagerAdapter<T>(private val fm: FragmentManager,
                                         private val pager: ViewPager?,
                                         private val startPos: Int? = 0)
    : FragmentStatePagerAdapter(fm), LifecycleObserver {

    var pagedList: PagedList<T>? = null
        private set
    private var callback = PagerCallback()

    private var isSecondTimes = false

    override fun getCount() = pagedList?.size ?: 0

    abstract fun createItem(position: Int): Fragment

    abstract var isSmoothScroll: Boolean

    override fun getItem(position: Int): Fragment {
        pagedList?.loadAround(position)
        return createItem(position)
    }

    fun submitList(pagedList: PagedList<T>?) {
        this.pagedList?.removeWeakCallback(callback)
        this.pagedList = pagedList
        this.pagedList?.addWeakCallback(null, callback)
        if (pager?.adapter?.count ?: 0 > 0) {
            scrollFirstToPos()
        }
        notifyDataSetChanged()
    }

    private inner class PagerCallback : PagedList.Callback() {
        override fun onChanged(position: Int, count: Int) =
                analyzeCount(position, count)

        override fun onInserted(position: Int, count: Int) =
                analyzeCount(position, count)

        override fun onRemoved(position: Int, count: Int) =
                analyzeCount(position, count)

        private fun analyzeCount(start: Int, count: Int) = analyzeRange(start, start + count)

        private fun analyzeRange(start: Int, end: Int) {
            notifyDataSetChanged()
            scrollFirstToPos()
        }
    }

    private fun scrollFirstToPos() {
        if (!isSecondTimes) {
            isSecondTimes = true
            pager?.setCurrentItem(startPos ?: 0, isSmoothScroll)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun cleanStack() {
        fm.beginTransaction()
                .remove(fm.fragments.removeAt(fm.fragments.size-3))
                .remove(fm.fragments.removeAt(fm.fragments.size-2))
                .remove(fm.fragments.removeAt(fm.fragments.size-1))
                .commit()
    }
}