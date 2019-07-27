package com.dudencovgmail.splashes.presentation.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.di.components.DaggerIViewPagerFragmentComponent
import com.dudencovgmail.splashes.di.components.IViewPagerFragmentComponent
import com.dudencovgmail.splashes.di.modules.ViewPagerFragmentModule
import com.dudencovgmail.splashes.di.components.injector
import com.dudencovgmail.splashes.presentation.notview.base.IViewPagerFragmentViewModel
import com.dudencovgmail.splashes.presentation.view.activities.MainActivity
import com.dudencovgmail.splashes.presentation.view.adapters.GalleryPagerAdapter
import com.dudencovgmail.splashes.util.inflate
import kotlinx.android.synthetic.main.fragment_viewpager.*
import javax.inject.Inject

class ViewPagerFragment : Fragment() {

    @Inject
    lateinit var adapter: GalleryPagerAdapter
    private var component: IViewPagerFragmentComponent? = null
    private val viewModel: IViewPagerFragmentViewModel by lazy { (activity as MainActivity).viewPagerVM }
    private var position: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return container?.inflate(R.layout.fragment_viewpager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDi()
        init()
        adapter.let { lifecycle.addObserver(adapter) }
        getList()
    }

    override fun onDestroy() {
        adapter.let { lifecycle.removeObserver(adapter) }
        component = null
        super.onDestroy()
    }

    private fun initDi() {
        component = DaggerIViewPagerFragmentComponent.builder()
                .iAppComponent((activity as MainActivity).injector)
                .viewPagerFragmentModule(ViewPagerFragmentModule(this))
                .build()
                .apply { inject(this@ViewPagerFragment) }
    }

    private fun init() {
        arguments?.let {
            val frArg = ViewPagerFragmentArgs.fromBundle(arguments!!)
            position = frArg.position
        }

        activity?.supportFragmentManager?.let {
            adapter.setPos(position)
            view_pager?.adapter = adapter
        }
    }

    private fun getList() {
        viewModel.pagedList?.observe(this, Observer { pagedList ->
            adapter.submitList(pagedList)
        })
    }
}