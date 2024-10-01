package com.pokedex

import android.app.Application
import com.pokedex.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup.onKoinStartup

@Suppress("OPT_IN_USAGE")
class PokedexApplication : Application() {

    init {
        onKoinStartup {
            androidLogger()
            androidContext(this@PokedexApplication)
            modules(networkModule)
        }
    }
}