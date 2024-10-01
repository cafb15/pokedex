package com.pokedex.data.datasource.remote.impl

import com.pokedex.data.api.PokedexApi
import com.pokedex.data.datasource.remote.PokedexRemoteDataSource
import com.pokedex.data.model.PokedexModel

class PokedexRemoteDataSourceImpl(
    private val api: PokedexApi
) : PokedexRemoteDataSource {

    override suspend fun getPokedex(limit: Int, offset: Int): Result<PokedexModel> {
        return try {
            Result.success(api.getPokedex(limit = limit, offset = offset))
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}