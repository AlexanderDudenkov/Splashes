package com.dudencovgmail.splashes.presentation

import android.support.multidex.MultiDexApplication
import com.dudencovgmail.splashes.BuildConfig
import com.dudencovgmail.splashes.di.components.DaggerIAppComponent
import com.dudencovgmail.splashes.di.components.IAppComponent
import com.dudencovgmail.splashes.di.components.IDaggerComponentProvider
import timber.log.Timber

class App : MultiDexApplication(), IDaggerComponentProvider {

    override val component: IAppComponent by lazy {
        DaggerIAppComponent.builder().applicationContext(applicationContext).build()
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
