package com.pokedex.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pokedex.ui.view.pokedex.PokedexScreen
import kotlinx.serialization.Serializable

@Serializable
data object PokedexRoute

fun NavGraphBuilder.pokedexDestination() = composable<PokedexRoute> { PokedexScreen() }