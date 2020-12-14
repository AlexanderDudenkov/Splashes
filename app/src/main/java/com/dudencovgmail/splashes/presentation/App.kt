package com.dudencovgmail.splashes.presentation

import android.support.multidex.MultiDexApplication
import com.dudencovgmail.splashes.BuildConfig
import com.dudencovgmail.splashes.di.components.DaggerAppComponent
import com.dudencovgmail.splashes.di.components.AppComponent
import com.dudencovgmail.splashes.di.components.DaggerComponentProvider
import timber.log.Timber

class App : MultiDexApplication(), DaggerComponentProvider {

    override val component: AppComponent by lazy {
        DaggerAppComponent.builder().applicationContext(applicationContext).build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
       // Realm.init(this)
        initTimberkt()
    }

    private fun initTimberkt() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        var instance: App? = null
    }
}
