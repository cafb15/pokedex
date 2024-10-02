package com.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pokedex.data.database.entity.PokemonEntity

@Dao
interface PokedexDao {

    @Query("SELECT * FROM pokemon")
    suspend fun getPokemons(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<PokemonEntity>)
}