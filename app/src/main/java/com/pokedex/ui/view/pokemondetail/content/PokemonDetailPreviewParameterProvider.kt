package com.pokedex.ui.view.pokemondetail.content

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.pokedex.domain.model.PokemonAbility
import com.pokedex.domain.model.PokemonAttack
import com.pokedex.domain.model.PokemonInfo
import com.pokedex.domain.model.PokemonType
import com.pokedex.ui.viewmodel.pokemondetail.PokemonDetailViewState

class PokemonDetailPreviewParameterProvider : PreviewParameterProvider<PokemonDetailViewState> {

    companion object {
        val pokemonInfo = PokemonInfo(
            name = "bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            types = listOf(
                PokemonType(name = "grass"),
                PokemonType(name = "poison")
            ),
            attacks = listOf(
                PokemonAttack(name = "tackle"),
                PokemonAttack(name = "growl")
            ),
            abilities = listOf(
                PokemonAbility(name = "overgrow"),
                PokemonAbility(name = "chlorophyll")
            )
        )
    }

    override val values: Sequence<PokemonDetailViewState> = sequenceOf(
        PokemonDetailViewState.Loading,
        PokemonDetailViewState.Success(pokemonInfo = pokemonInfo)
    )
}