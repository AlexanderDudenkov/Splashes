package com.dudencovgmail.splashes.presentation.view.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.navigation.fragment.NavHostFragment.*
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.di.components.injector
import com.dudencovgmail.splashes.presentation.notview.base.IMainFragmentViewModel
import com.dudencovgmail.splashes.presentation.notview.base.IViewPagerFragmentViewModel
import com.dudencovgmail.splashes.presentation.notview.viewmodels.MainFragmentViewModel
import com.dudencovgmail.splashes.presentation.notview.viewmodels.ViewPagerFragmentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val mainViewVM: IMainFragmentViewModel by lazy {
        ViewModelProviders.of(this, injector.mainViewModelFactory()).get(MainFragmentViewModel::class.java)
    }
    val viewPagerVM: IViewPagerFragmentViewModel by lazy {
        ViewModelProviders.of(this, injector.viewPagerViewModelFactory()).get(ViewPagerFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() = findNavController(nav_host_fragment).navigateUp()
}
