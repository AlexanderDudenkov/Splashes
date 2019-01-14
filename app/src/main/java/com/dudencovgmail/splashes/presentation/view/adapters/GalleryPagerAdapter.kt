package com.dudencovgmail.splashes.presentation.view.adapters

import android.support.v4.app.Fragment
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.presentation.view.base.APagedListPagerAdapter
import com.dudencovgmail.splashes.presentation.view.activities.DetailActivity
import com.dudencovgmail.splashes.presentation.view.fragments.DetailFragment
import kotlinx.android.synthetic.main.activity_detail.*

class GalleryPagerAdapter(activity: DetailActivity, startPos: Int? = 0)
    : APagedListPagerAdapter<Model>(activity.supportFragmentManager,
        activity.view_pager, startPos) {

    override var isSmoothScroll = false

    override fun createItem(position: Int): Fragment {
        return DetailFragment.newInstance(position)
    }
}