package com.pokedex.domain.repository

import com.pokedex.domain.model.Pokedex

interface PokedexRepository {

    suspend fun getPokedex(limit: Int, offset: Int): Result<Pokedex>
}