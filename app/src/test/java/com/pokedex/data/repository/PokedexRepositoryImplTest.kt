package com.pokedex.data.repository

import com.pokedex.data.datasource.local.PokedexLocalDataSource
import com.pokedex.data.datasource.remote.PokedexRemoteDataSource
import com.pokedex.data.model.PokedexModelFactory
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Test

class PokedexRepositoryImplTest {

    private val mockPokedexLocalDataSource = mockk<PokedexLocalDataSource>(relaxed = true)
    private val mockPokedexRemoteDataSource = mockk<PokedexRemoteDataSource>()

    private val pokedexRepository = PokedexRepositoryImpl(
        remoteDataSource = mockPokedexRemoteDataSource,
        localDataSource = mockPokedexLocalDataSource
    )

    @After
    fun shutDown() {
        clearAllMocks()
    }

    @Test
    fun `should get a Pokedex and save locally the first page`() = runTest {
        val pokemonEntityList = PokedexModelFactory.pokedexEntityList

        coEvery {
            mockPokedexRemoteDataSource.getPokedex(limit = any(), offset = any())
        } returns Result.success(PokedexModelFactory.pokedexModel)

        val result = pokedexRepository.getPokedex(limit = 150, offset = 0)

        coVerify(exactly = 1) {
            mockPokedexRemoteDataSource.getPokedex(limit = 150, offset = 0)
            mockPokedexLocalDataSource.saveFirstPagePokedex(pokemonEntityList)
        }

        result.isSuccess shouldBeEqualTo true

        result.onSuccess {
            it.isLastPage shouldBeEqualTo false
            it.pokemons.size shouldBeEqualTo 5

            with(it.pokemons.first()) {
                name shouldBeEqualTo "bulbasaur"
                url shouldBeEqualTo "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            }
        }

        pokedexRepository.getPokedex(limit = 150, offset = 150)

        coVerify(exactly = 1) {
            mockPokedexRemoteDataSource.getPokedex(limit = 150, offset = 150)
            mockPokedexLocalDataSource.saveFirstPagePokedex(pokemonEntityList)
        }
    }
}