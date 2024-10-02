package com.pokedex.data.datasource.local

import com.pokedex.data.database.entity.PokemonEntity

interface PokedexLocalDataSource {

    suspend fun saveFirstPagePokedex(pokemons: List<PokemonEntity>)

    suspend fun getPokemons(): List<PokemonEntity>
}