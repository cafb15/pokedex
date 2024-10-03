package com.pokedex.domain.model

data class Pokedex(
    val isLastPage: Boolean = false,
    val pokemons: List<Pokemon> = emptyList()
)

data class Pokemon(
    val name: String = "",
    val url: String = ""
)