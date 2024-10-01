package com.pokedex.data.datasource.remote

import com.pokedex.data.model.PokedexModel

interface PokedexRemoteDataSource {

    suspend fun getPokedex(limit: Int, offset: Int): Result<PokedexModel>
}