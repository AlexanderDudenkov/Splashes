package com.dudencovgmail.splashes.presentation.view.activities

import android.os.Bundle
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.presentation.view.base.BaseActivity
import com.dudencovgmail.splashes.presentation.notview.viewmodels.MainViewModel
import com.dudencovgmail.splashes.presentation.view.fragments.MainFragment
import com.dudencovgmail.splashes.util.obtainViewModel
import com.dudencovgmail.splashes.util.startFragmentAnim

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_activity)
        setupViewFragment()
    }

    private fun setupViewFragment() {
        startFragmentAnim(MainFragment.newInstance(), R.id.fragmentContainer)
    }

    fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)
}
