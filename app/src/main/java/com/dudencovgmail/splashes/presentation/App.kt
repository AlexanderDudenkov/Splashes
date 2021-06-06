package com.dudencovgmail.splashes.presentation

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {
        var instance: App? = null
    }
}
