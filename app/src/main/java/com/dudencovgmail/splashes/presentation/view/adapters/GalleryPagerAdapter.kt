package com.dudencovgmail.splashes.presentation.view.adapters

import android.support.v4.app.Fragment
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.presentation.view.base.APagedListPagerAdapter
import com.dudencovgmail.splashes.presentation.view.fragments.ItemViewPagerFragment
import com.dudencovgmail.splashes.presentation.view.fragments.ViewPagerFragment
import kotlinx.android.synthetic.main.fragment_viewpager.*

class GalleryPagerAdapter(fr: ViewPagerFragment)
    : APagedListPagerAdapter<Model>(fr.activity?.supportFragmentManager!!, fr.view_pager) {

    override var isSmoothScroll = false

    override fun createItem(position: Int): Fragment {
        return ItemViewPagerFragment.newInstance(position)
    }

    fun setPos(pos: Int) {
        startPos = pos
    }
}