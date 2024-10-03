package com.pokedex.ui.viewmodel.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokedex.domain.model.PokemonInfo
import com.pokedex.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val pokedexRepository: PokedexRepository
) : ViewModel() {

    private var _isLoading = MutableStateFlow(false)
    private var _pokemonInfo = MutableStateFlow(PokemonInfo())

    val pokemonDetailViewState: StateFlow<PokemonDetailViewState> = combine(
        _isLoading,
        _pokemonInfo,
        ::calculateViewState
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PokemonDetailViewState.Loading
    )

    private fun calculateViewState(
        isLoading: Boolean,
        pokemonInfo: PokemonInfo
    ): PokemonDetailViewState {
        return if (isLoading) {
            PokemonDetailViewState.Loading
        } else {
            PokemonDetailViewState.Success(pokemonInfo)
        }
    }

    fun getPokemonInfo(pokemonName: String) {
        viewModelScope.launch {
            _isLoading.update { true }

            val result = pokedexRepository.getPokemonInfo(pokemonName)

            result.onSuccess { pokemonInfo ->
                _pokemonInfo.update { pokemonInfo }
            }

            _isLoading.update { false }
        }
    }
}