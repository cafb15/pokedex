package com.pokedex.data.repository

import com.pokedex.data.datasource.local.PokedexLocalDataSource
import com.pokedex.data.datasource.remote.PokedexRemoteDataSource
import com.pokedex.domain.model.Pokedex
import com.pokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokedexRepositoryImpl(
    private val remoteDataSource: PokedexRemoteDataSource,
    private val localDataSource: PokedexLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokedexRepository {

    override suspend fun getPokedex(limit: Int, offset: Int): Result<Pokedex> = withContext(ioDispatcher) {
        val result = remoteDataSource.getPokedex(limit = limit, offset = offset)

        result.fold(
            onSuccess = { pokedexModel ->
                if (offset == 0) {
                    localDataSource.saveFirstPagePokedex(pokedexModel.results.map { it.toEntity() })
                }

                return@withContext Result.success(pokedexModel.toDomain())
            },
            onFailure = {
                return@withContext Result.success(
                    Pokedex(
                        isLastPage = true,
                        pokemons = localDataSource.getPokemons().map { it.toDomain() }
                    )
                )
            }
        )
    }
}