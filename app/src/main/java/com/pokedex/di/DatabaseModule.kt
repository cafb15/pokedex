package com.pokedex.di

import androidx.room.Room
import com.pokedex.data.database.PokedexDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = PokedexDatabase::class.java,
            name = "pokedex_database"
        ).build()
    }
}