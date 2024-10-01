package com.pokedex.data.repository

import com.pokedex.data.datasource.remote.PokedexRemoteDataSource
import com.pokedex.data.model.PokedexModel
import com.pokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokedexRepositoryImpl(
    private val remoteDataSource: PokedexRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokedexRepository {

    override suspend fun getPokedex(limit: Int, offset: Int): Result<PokedexModel> = withContext(ioDispatcher) {
        remoteDataSource.getPokedex(limit = limit, offset = offset)
    }
}