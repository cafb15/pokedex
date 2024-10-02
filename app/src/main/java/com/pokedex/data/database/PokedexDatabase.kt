package com.pokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pokedex.data.database.dao.PokedexDao
import com.pokedex.data.database.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokedexDao(): PokedexDao
}