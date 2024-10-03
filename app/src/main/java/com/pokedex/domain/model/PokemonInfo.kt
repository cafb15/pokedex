package com.pokedex.domain.model

data class PokemonInfo(
    val name: String = "",
    val imageUrl: String = "",
    val types: List<PokemonType> = emptyList(),
    val attacks: List<PokemonAttack> = emptyList(),
    val abilities: List<PokemonAbility> = emptyList()
)
