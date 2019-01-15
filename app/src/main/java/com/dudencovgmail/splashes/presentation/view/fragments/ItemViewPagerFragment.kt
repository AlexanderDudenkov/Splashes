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
import com.dudencovgmail.splashes.presentation.notview.viewmodels.ItemViewPagerFrVM
import com.dudencovgmail.splashes.presentation.view.activities.MainActivity
import com.dudencovgmail.splashes.util.inflate
import com.dudencovgmail.splashes.util.loadImage
import com.dudencovgmail.splashes.util.obtainViewModel
import kotlinx.android.synthetic.main.image_view.*

class ItemViewPagerFragment : Fragment() {

    var viewModel: ItemViewPagerFrVM? = null
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
        viewModel = obtainViewModel()
        getList()
        return view
    }

    private fun getList() {
        viewModel?.pagedList?.observe(this, Observer { items ->
            pagedList = items
            if (pagedList != null) {
                iv.loadImage(pagedList!![pos]?.photosUrl?.urlMedium)
            }
        })
    }

    private fun obtainViewModel(): ItemViewPagerFrVM =
            (activity as MainActivity).obtainViewModel(ItemViewPagerFrVM::class.java)

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
