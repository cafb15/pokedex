package com.pokedex.data.datasource.remote

import com.pokedex.data.model.PokedexModel
import com.pokedex.data.model.PokemonInfoModel

interface PokedexRemoteDataSource {

    suspend fun getPokedex(limit: Int, offset: Int): Result<PokedexModel>

    suspend fun getPokemonInfo(pokemonName: String): Result<PokemonInfoModel>
}