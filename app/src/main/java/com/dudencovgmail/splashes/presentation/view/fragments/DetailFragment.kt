package com.dudencovgmail.splashes.presentation.view.fragments

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.data.Model
import com.dudencovgmail.splashes.presentation.notview.base.ADetailViewModel
import com.dudencovgmail.splashes.presentation.view.activities.DetailActivity
import com.dudencovgmail.splashes.util.inflate
import com.dudencovgmail.splashes.util.loadImage
import kotlinx.android.synthetic.main.gallery_item.*

class DetailFragment : Fragment() {

    private var viewModel: ADetailViewModel? = null
    private var pagedList: PagedList<Model>? = null
    private var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        pos = arguments?.getInt(ARG_ID) ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.gallery_item)
        viewModel = (activity as DetailActivity).obtainViewModel()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getList()
    }

    private fun getList() {
        viewModel?.pagedList?.observe(this, Observer { items ->
            pagedList = items
            if (pagedList != null) {
                iv.loadImage(pagedList!![pos]?.photosUrl?.urlMedium)
            }
        })
    }

    companion object {
        private const val ARG_ID = "id"

        fun newInstance(pos: Int): DetailFragment {
            val args = Bundle()
            args.putInt(ARG_ID, pos)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
