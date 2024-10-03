package com.pokedex.domain.repository

import com.pokedex.domain.model.Pokedex
import com.pokedex.domain.model.PokemonInfo

interface PokedexRepository {

    suspend fun getPokedex(limit: Int, offset: Int): Result<Pokedex>

    suspend fun getPokemonInfo(pokemonName: String): Result<PokemonInfo>
}