package com.pokedex.domain.repository

import com.pokedex.data.model.PokedexModel

interface PokedexRepository {

    suspend fun getPokedex(limit: Int, offset: Int): Result<PokedexModel>
}