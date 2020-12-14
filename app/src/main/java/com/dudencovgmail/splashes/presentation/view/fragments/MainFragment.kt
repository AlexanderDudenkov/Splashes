package com.dudencovgmail.splashes.presentation.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.di.components.DaggerIMainFragmentComponent
import com.dudencovgmail.splashes.di.components.IMainFragmentComponent
import com.dudencovgmail.splashes.di.components.injector
import com.dudencovgmail.splashes.di.modules.MainFragmentModule
import com.dudencovgmail.splashes.presentation.notview.base.IMainFragmentViewModel
import com.dudencovgmail.splashes.presentation.view.activities.MainActivity
import com.dudencovgmail.splashes.presentation.view.adapters.GalleryAdapter2
import com.dudencovgmail.splashes.util.inflate
import com.dudencovgmail.splashes.util.showProgress
import com.dudencovgmail.splashes.util.toast
import com.github.ajalt.timberkt.Timber.d
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var galleryAdapter: GalleryAdapter2
    private var component: IMainFragmentComponent? = null
    private val viewModel: IMainFragmentViewModel by lazy { (activity as MainActivity).mainViewVM }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return container?.inflate(R.layout.fragment_main)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDi()
        showProgress()
        showError()
        init()
        getList()
    }

    override fun onDestroy() {
        component = null
        super.onDestroy()
    }

    private fun initDi() {
        component = DaggerIMainFragmentComponent.builder()
                .appComponent((activity as MainActivity).injector)
                .mainFragmentModule(MainFragmentModule(activity as MainActivity))
                .build()
                .apply { inject(this@MainFragment) }
    }

    private fun init() {
        rv.layoutManager = GridLayoutManager(activity, 3)

        galleryAdapter.setOnClickListener { pos: Int -> startViewPagerFragment(pos) }
        rv.adapter = galleryAdapter
    }

    private fun startViewPagerFragment(pos: Int) {
        val action = MainFragmentDirections.actionMainFragmentToViewPagerFragment()
        action.position = pos
        activity?.let {
            findNavController(it, R.id.nav_host_fragment).navigate(action)
        }
    }

    private fun showProgress() {
        viewModel.progress.observe(this, Observer<Boolean> { t ->
            pb_rv.showProgress(activity, t ?: false)
        })
    }

    private fun getList() {
        viewModel.pagedList?.observe(this, Observer { pagedList ->
            galleryAdapter.submitList(pagedList)
        })
    }

    private fun showError() {
        viewModel.errorMessage.observe(this, Observer { error ->
            context?.toast(error)
            d { error ?: "errorMessage" }
        })
    }
}
