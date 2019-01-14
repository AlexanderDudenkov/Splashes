package com.dudencovgmail.splashes.presentation.view.activities

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.presentation.view.adapters.GalleryPagerAdapter
import com.dudencovgmail.splashes.presentation.view.base.BaseActivity
import com.dudencovgmail.splashes.presentation.notview.base.ADetailViewModel
import com.dudencovgmail.splashes.presentation.notview.viewmodels.DetailViewModel
import com.dudencovgmail.splashes.util.obtainViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {

    private var viewModel: ADetailViewModel? = null
    private var position: Int = 0
    private var adapter: GalleryPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
        getList()
    }

    private fun init() {
        position = intent.getIntExtra(EXTRA_POSITION_RV, 0)
        viewModel = obtainViewModel()

        adapter = GalleryPagerAdapter(this, position)
        view_pager.adapter = adapter
    }

    private fun getList() {
        viewModel?.pagedList?.observe(this, Observer { items ->
            adapter?.submitList(items)
        })
    }

    fun obtainViewModel(): DetailViewModel = obtainViewModel(DetailViewModel::class.java)

    companion object {
        private val EXTRA_POSITION_RV = "com.dudencovgmail.splashes.presentation.view.detailScreen.positionRV"

        fun newIntent(position: Int, packageContext: Context): Intent {
            val intent = Intent(packageContext, DetailActivity::class.java)
            intent.putExtra(EXTRA_POSITION_RV, position)
            return intent
        }
    }
}
