package com.dudencovgmail.splashes.presentation

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.dudencovgmail.splashes.BuildConfig
import com.dudencovgmail.splashes.util.isOnline
import timber.log.Timber

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initTimberkt()
        isOnline = appContext().isOnline()
    }

    private fun initTimberkt() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        var isOnline: Boolean? = null
        private var instance: App? = null
        fun appContext(): Context {
            return instance!!.applicationContext
        }
    }
}
