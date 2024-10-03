package com.pokedex.ui.viewmodel.pokemondetail

import com.pokedex.domain.model.PokemonInfo

sealed interface PokemonDetailViewState {

    data object Loading : PokemonDetailViewState

    data class Success(val pokemonInfo: PokemonInfo) : PokemonDetailViewState
}