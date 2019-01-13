package com.dudencovgmail.splashes.presentation.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.presentation.adapters.GalleryAdapter
import com.dudencovgmail.splashes.presentation.viewmodels.AMainViewModel
import com.dudencovgmail.splashes.util.*
import com.github.ajalt.timberkt.Timber.d
import kotlinx.android.synthetic.main.fragment_gallery.*

class MainFragment : Fragment() {

    private var galleryAdapter: GalleryAdapter? = null
    private var pagedList: LiveData<PagedList<Model>>? = null
    private var viewModel: AMainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_gallery)
        viewModel = (activity as MainActivity).obtainViewModel()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showProgress()
        showError()
        init()
        getList()
    }

    private fun init() {
        rv.layoutManager = GridLayoutManager(activity, 3)

        val func = { pos: Int -> startActivity(DetailActivity.newIntent(pos, context!!)) }
        galleryAdapter = GalleryAdapter(func)
        rv.adapter = galleryAdapter
    }

    private fun showProgress() {
        viewModel?.progress?.observe(this, Observer<Boolean> { t ->
            pb_rv.showProgress(activity, t ?: false)
        })
    }

    private fun getList() {
        this.pagedList = viewModel?.pagedList
        this.pagedList?.observe(this, Observer { items ->
            galleryAdapter?.submitList(items)
        })
    }

    private fun showError() {
        viewModel?.error?.observe(this, Observer { error ->
            context?.toast(error)
            d { error!! }
        })
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
