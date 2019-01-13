package com.dudencovgmail.splashes.presentation.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.dudencovgmail.splashes.R
import com.dudencovgmail.splashes.util.key

@SuppressLint("Registered")

abstract class BaseActivity : AppCompatActivity(){

    override fun onBackPressed() {
        super.onBackPressed()
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}