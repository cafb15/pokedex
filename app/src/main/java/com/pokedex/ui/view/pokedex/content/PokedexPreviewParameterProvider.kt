package com.pokedex.ui.view.pokedex.content

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pokedex.domain.model.Pokemon
import com.pokedex.ui.viewmodel.pokedex.PokedexViewState

class PokedexPreviewParameterProvider : PreviewParameterProvider<PokedexViewState> {

    companion object {

        val pokemons = listOf(
            Pokemon(
                name = "Bulbasaur",
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            ),
            Pokemon(
                name = "Ivysaur",
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png"
            ),
            Pokemon(
                name = "Venusaur",
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png"
            ),
            Pokemon(
                name = "Charmander",
                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
            )
        )
    }

    override val values: Sequence<PokedexViewState> = sequenceOf(
        PokedexViewState.Loading,
        PokedexViewState.Success(
            isPaginating = false,
            pokemons = pokemons,
            pokemonNameFilter = ""
        )
    )
}