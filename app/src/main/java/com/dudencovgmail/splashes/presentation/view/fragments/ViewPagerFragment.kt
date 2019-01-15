package com.dudencovgmail.splashes.presentation.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.presentation.notview.viewmodels.ViewPagerFragmentViewModel
import com.dudencovgmail.splashes.presentation.view.activities.MainActivity
import com.dudencovgmail.splashes.presentation.view.adapters.GalleryPagerAdapter
import com.dudencovgmail.splashes.util.inflate
import com.dudencovgmail.splashes.util.obtainViewModel
import kotlinx.android.synthetic.main.fragment_viewpager.*

class ViewPagerFragment : Fragment() {
    private var position: Int = 0
    var viewModel: ViewPagerFragmentViewModel? = null
    private var adapter: GalleryPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_viewpager)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getList()
    }

    private fun init() {
        if (arguments != null) {
            val frArg = ViewPagerFragmentArgs.fromBundle(arguments!!)
            position = frArg.position
        }
        viewModel = obtainViewModel()

        adapter = GalleryPagerAdapter(this, position)
        view_pager.adapter = adapter
    }

    private fun getList() {
        viewModel?.pagedList?.observe(this, Observer { items ->
            adapter?.submitList(items)
        })
    }

    private fun obtainViewModel(): ViewPagerFragmentViewModel =
            (activity as MainActivity).obtainViewModel(ViewPagerFragmentViewModel::class.java)
}