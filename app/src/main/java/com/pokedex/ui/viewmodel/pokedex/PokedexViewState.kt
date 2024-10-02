package com.pokedex.ui.viewmodel.pokedex

import com.pokedex.domain.model.Pokemon

sealed interface PokedexViewState {

    data object Loading : PokedexViewState

    data class Success(
        val isPaginating: Boolean,
        val pokemons: List<Pokemon>,
        val pokemonNameFilter: String
    ) : PokedexViewState
}