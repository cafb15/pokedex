package com.pokedex.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.pokedex.ui.view.pokedex.PokedexScreen
import com.pokedex.ui.view.pokemondetail.PokemonDetailScreen
import kotlinx.serialization.Serializable

@Serializable
data object PokedexRoute

@Serializable
data class PokemonDetailRoute(val pokemonName: String)

fun NavGraphBuilder.pokedexDestination() = composable<PokedexRoute> { PokedexScreen() }

fun NavGraphBuilder.pokemonDetailDestination() = composable<PokemonDetailRoute> { backStackEntry ->
    val route = backStackEntry.toRoute<PokemonDetailRoute>()
    PokemonDetailScreen(route.pokemonName)
}

fun NavHostController.navigateToPokemonDetail(pokemonName: String) = navigate(
    route = PokemonDetailRoute(pokemonName)
)