package com.dudencovgmail.splashes.presentation.view.adapters

import android.support.v4.app.Fragment
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.presentation.view.base.APagedListPagerAdapter
import com.dudencovgmail.splashes.presentation.view.fragments.ItemViewPagerFragment
import com.dudencovgmail.splashes.presentation.view.fragments.ViewPagerFragment
import kotlinx.android.synthetic.main.fragment_viewpager.*

class GalleryPagerAdapter(fr:ViewPagerFragment, startPos: Int? = 0)
    : APagedListPagerAdapter<Model>(fr.activity?.supportFragmentManager!!, fr.activity?.view_pager, startPos) {

    override var isSmoothScroll = false

    init {
        fr.lifecycle.addObserver(this)
    }

    override fun createItem(position: Int): Fragment {
        return ItemViewPagerFragment.newInstance(position)
    }

}