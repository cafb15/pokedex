package com.pokedex.domain.usecase

import com.pokedex.data.model.PokedexModelFactory
import com.pokedex.domain.repository.PokedexRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Test

class GetPokedexUseCaseTest {

    private val mockPokedexRepository = mockk<PokedexRepository>()

    private val getPokedexUseCase = GetPokedexUseCase(mockPokedexRepository)

    @After
    fun shutDown() {
        clearAllMocks()
    }

    @Test
    fun `should get a pokedex successfully`() = runTest {
        coEvery {
            mockPokedexRepository.getPokedex(limit = any(), offset = any())
        } returns Result.success(PokedexModelFactory.pokedex)

        val result = getPokedexUseCase.invoke(page = 0)

        result.isSuccess shouldBeEqualTo true

        result.onSuccess {
            it.isLastPage shouldBeEqualTo false
            it.pokemons.size shouldBeEqualTo 5
            it.pokemons shouldBeEqualTo PokedexModelFactory.pokedex.pokemons
        }

        coVerify(exactly = 1) { mockPokedexRepository.getPokedex(limit = 150, offset = 0) }
    }
}