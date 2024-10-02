package com.pokedex.ui.view.pokedex.content

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pokedex.domain.model.Pokemon
import com.pokedex.ui.viewmodel.pokedex.PokedexViewState

class PokedexPreviewParameterProvider : PreviewParameterProvider<PokedexViewState> {

    companion object {

        val pokemons = listOf(
            Pokemon(
                name = "Bulbasaur",
                url = "https://pokeapi.co/api/v2/pokemon/1/"
                //url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
            ),
            Pokemon(
                name = "Ivysaur",
                url = "https://pokeapi.co/api/v2/pokemon/2/"
            ),
            Pokemon(
                name = "Venusaur",
                url = "https://pokeapi.co/api/v2/pokemon/3/"
            ),
            Pokemon(
                name = "Charmander",
                url = "https://pokeapi.co/api/v2/pokemon/4/"
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