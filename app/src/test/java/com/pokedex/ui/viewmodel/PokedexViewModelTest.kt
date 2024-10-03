package com.pokedex.ui.viewmodel

import com.pokedex.data.model.PokedexModelFactory
import com.pokedex.domain.usecase.GetPokedexUseCase
import com.pokedex.ui.viewmodel.pokedex.PokedexViewModel
import com.pokedex.ui.viewmodel.pokedex.PokedexViewState
import com.pokedex.utils.TestCoroutineRule
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokedexViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mockGetPokemonUseCase = mockk<GetPokedexUseCase>()

    private lateinit var viewModel: PokedexViewModel

    @Before
    fun setup() {
        viewModel = PokedexViewModel(
            getPokedexUseCase = mockGetPokemonUseCase
        )
    }

    @After
    fun shutDown() {
        clearAllMocks()
    }

    @Test
    fun `should show first page of pokemons`() = runTest {
        coEvery {
            mockGetPokemonUseCase.invoke(page = any())
        } coAnswers {
            Result.success(PokedexModelFactory.pokedex)
        }

        viewModel.getFirstPagePokedex()

        val emissions = mutableListOf<PokedexViewState>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.pokedexViewState.toList(emissions)
        }

        emissions[0] shouldBeInstanceOf PokedexViewState.Loading::class
        emissions[1] shouldBeInstanceOf PokedexViewState.Success::class
        emissions[1] shouldBeEqualTo PokedexViewState.Success(
            isPaginating = false,
            pokemons = PokedexModelFactory.pokedex.pokemons,
            pokemonNameFilter = ""
        )

        coVerify(exactly = 1) { mockGetPokemonUseCase.invoke(page = 0) }
    }
}