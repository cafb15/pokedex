package com.pokedex.ui.view.pokemondetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pokedex.ui.view.pokemondetail.content.PokemonDetailContent
import com.pokedex.ui.viewmodel.pokemondetail.PokemonDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonDetailScreen(
    pokemonName: String
) {
    val viewModel = koinViewModel<PokemonDetailViewModel>()

    val viewState by viewModel.pokemonDetailViewState.collectAsStateWithLifecycle()

    PokemonDetailContent(viewState = viewState)

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getPokemonInfo(pokemonName)
    })
}