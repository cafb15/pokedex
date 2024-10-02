package com.pokedex.di

import android.content.Context
import android.net.ConnectivityManager
import com.pokedex.BuildConfig
import com.pokedex.core.data.interceptor.NetworkChecker
import com.pokedex.core.data.interceptor.NetworkInterceptor
import com.pokedex.data.api.PokedexApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {

    single {
        NetworkChecker(
            connectivityManager = get<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )
    }

    single {
        okhttp(
            networkInterceptor = NetworkInterceptor(networkChecker = get())
        )
    }

    single {
        retrofit(okHttpClient = get())
    }

    single { get<Retrofit>().create(PokedexApi::class.java) }
}

private val jsonConverter = Json { ignoreUnknownKeys = true }

private fun retrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(jsonConverter.asConverterFactory("application/json".toMediaType()))
    .client(okHttpClient)
    .baseUrl("https://pokeapi.co/api/v2/")
    .build()

private fun okhttp(networkInterceptor: NetworkInterceptor): OkHttpClient = OkHttpClient.Builder()
    .apply {
        if (BuildConfig.DEBUG) {
            addNetworkInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            )
        }
    }
    .addNetworkInterceptor(networkInterceptor)
    .build()