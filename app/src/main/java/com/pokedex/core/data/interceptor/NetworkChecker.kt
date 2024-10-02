package com.pokedex.core.data.interceptor

import android.net.ConnectivityManager

class NetworkChecker(
    private val connectivityManager: ConnectivityManager
) {

    fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork
        return network != null
    }
}