package com.pokedex.core.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(
    private val networkChecker: NetworkChecker
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkChecker.isNetworkAvailable()) {
            throw NoNetworkException()
        }

        return chain.proceed(chain.request())
    }
}