package com.dudencovgmail.splashes.presentation.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment.*
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.presentation.notview.base.AMainFragmentViewModel
import com.dudencovgmail.splashes.presentation.notview.viewmodels.MainFragmentViewModel
import com.dudencovgmail.splashes.util.obtainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mainViewModel: AMainFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = obtainMainViewModel()
    }

    override fun onSupportNavigateUp() = findNavController(nav_host_fragment).navigateUp()

    private fun obtainMainViewModel(): MainFragmentViewModel = obtainViewModel(MainFragmentViewModel::class.java)
}
