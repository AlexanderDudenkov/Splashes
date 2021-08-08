package com.dudencovgmail.splashes.presentation

import android.app.Application
import com.dudencovgmail.splashes.data.NetworkManager
import com.dudencovgmail.splashes.utils.registerActivityLifecycleCallbacks
import dagger.Lazy
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var networkManager: Lazy<NetworkManager>

    override fun onCreate() {
        super.onCreate()
        instance = this

        registerActivityLifecycleCallbacks(
            onActivityStarted = {
                networkManager.get().registerNetworkCallback()
            },
            onActivityStopped = { networkManager.get().unregisterNetworkCallback() }
        )
    }

    companion object {
        var instance: App? = null
    }
}
