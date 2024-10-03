package com.pokedex.data.api

import com.pokedex.data.model.PokedexModel
import com.pokedex.data.model.PokemonInfoModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {

    @GET("pokemon")
    suspend fun getPokedex(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokedexModel

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonInfo(@Path("pokemonName") pokemonName: String): PokemonInfoModel
}