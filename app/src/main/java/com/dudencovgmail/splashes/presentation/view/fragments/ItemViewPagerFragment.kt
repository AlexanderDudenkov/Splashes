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
import com.dudencovgmail.splashes.domain.Interactor
import com.dudencovgmail.splashes.domain.ModelListBuilder
import com.dudencovgmail.splashes.presentation.notview.base.IViewPagerFragmentViewModel
import com.dudencovgmail.splashes.presentation.notview.viewmodels.ViewPagerFragmentViewModel
import com.dudencovgmail.splashes.presentation.view.activities.MainActivity
import com.dudencovgmail.splashes.repository.Repository
import com.dudencovgmail.splashes.util.generateRandomColor
import com.dudencovgmail.splashes.util.inflate
import com.dudencovgmail.splashes.util.loadImage
import com.github.ajalt.timberkt.Timber.d
import kotlinx.android.synthetic.main.image_view.*

class ItemViewPagerFragment : Fragment() {

    private val viewModel: IViewPagerFragmentViewModel by lazy { (activity as MainActivity).viewPagerVM }
    private var pagedList: PagedList<Model>? = null
    private var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        pos = arguments?.getInt(ARG_ID) ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_item_viewpager)
        view?.setBackgroundColor(generateRandomColor())
        getList()
        return view
    }

    private fun getList() {
        viewModel.pagedList?.observe(this, Observer { pagedList ->
            this.pagedList = pagedList
            if (this.pagedList != null) {
                iv.loadImage(this.pagedList!![pos]?.photosUrl?.urlMedium)
            }
        })
    }

    companion object {
        private const val ARG_ID = "id"

        fun newInstance(pos: Int): ItemViewPagerFragment {
            val args = Bundle()
            args.putInt(ARG_ID, pos)
            val fragment = ItemViewPagerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
