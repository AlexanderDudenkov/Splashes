package com.dudencovgmail.splashes.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkManager @Inject constructor(context: Context) {

    private val _observeNetworkActivity = MutableSharedFlow<Boolean>(1)
    val observeNetworkActivity: SharedFlow<Boolean> = _observeNetworkActivity

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isDefaultNetworkActive = connectivityManager.isDefaultNetworkActive

    private var hasNetworkChanged: Boolean = false

    private val networkStateObject = object : ConnectivityManager.NetworkCallback() {
        override fun onLost(network: Network) {
            super.onLost(network)
            hasNetworkChanged = true
            _observeNetworkActivity.tryEmit(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            hasNetworkChanged = true
            _observeNetworkActivity.tryEmit(false)
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            if (hasNetworkChanged) {
                _observeNetworkActivity.tryEmit(true)
            }
        }
    }

    fun registerNetworkCallback() {
        connectivityManager.registerNetworkCallback(networkRequest(), networkStateObject)
    }

    fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkStateObject)
    }

    private fun networkRequest(): NetworkRequest {
        return NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
    }
}
