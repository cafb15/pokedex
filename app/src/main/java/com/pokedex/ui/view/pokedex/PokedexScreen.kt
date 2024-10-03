package com.pokedex.ui.view.pokedex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pokedex.ui.navigation.LocalNavController
import com.pokedex.ui.navigation.navigateToPokemonDetail
import com.pokedex.ui.view.pokedex.content.PokedexContent
import com.pokedex.ui.viewmodel.pokedex.PokedexViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokedexScreen() {
    val navController = LocalNavController.current
    val viewModel = koinViewModel<PokedexViewModel>()

    val viewState by viewModel.pokedexViewState.collectAsStateWithLifecycle()

    PokedexContent(
        viewState = viewState,
        onValueChange = viewModel::filterPokemons,
        onNextPage = viewModel::getNexPagePokedex,
        onPokemonClick = navController::navigateToPokemonDetail
    )

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getFirstPagePokedex()
    })
}