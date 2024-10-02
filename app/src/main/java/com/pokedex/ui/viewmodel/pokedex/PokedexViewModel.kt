package com.pokedex.ui.viewmodel.pokedex

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokedex.domain.model.Pokemon
import com.pokedex.domain.usecase.GetPokedexUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokedexViewModel(
    val  getPokedexUseCase: GetPokedexUseCase
) : ViewModel() {

    private var currentPage: Int = 0
    private var isLastPage: Boolean = false

    private var _isLoading = MutableStateFlow(false)
    private var _isPaginating = MutableStateFlow(false)
    private var _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())

    val pokedexViewState: StateFlow<PokedexViewState> = combine(
        _isLoading,
        _isPaginating,
        _pokemons,
        ::calculateViewState
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PokedexViewState.Loading
    )

    private fun calculateViewState(
        isLoading: Boolean,
        isPaginating: Boolean,
        pokemons: List<Pokemon>
    ): PokedexViewState {
        return if (isLoading) {
            PokedexViewState.Loading
        } else {
            PokedexViewState.Success(
                isPaginating = isPaginating,
                pokemons = pokemons
            )
        }
    }

    fun getFirstPagePokedex() {
        Log.d("PokedexViewModel", "getFirstPagePokedex")
        viewModelScope.launch {
            _isLoading.update { true }

            getPokedex(currentPage)

            _isLoading.update { false }
        }
    }

    fun getNexPagePokedex() {
        viewModelScope.launch {
            if (!isLastPage) {
                Log.d("PokedexViewModel", "getNextPagePokedex: page: $currentPage")
                _isPaginating.update { true }

                getPokedex(currentPage)

                _isPaginating.update { false }
            }
        }
    }

    private suspend fun getPokedex(page: Int) {
        val result = getPokedexUseCase(page)

        result.onSuccess { pokedex ->
            currentPage++
            isLastPage = pokedex.isLastPage
            _pokemons.update { it + pokedex.pokemons }
        }
    }
}