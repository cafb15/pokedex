package com.pokedex.domain.model

data class Pokedex(
    val isLastPage: Boolean,
    val pokemons: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String
)