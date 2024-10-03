package com.pokedex.data.model

object PokedexModelFactory {

    val pokedexModel = PokedexModel(
        count = 1302,
        next = "https://pokeapi.co/api/v2/pokemon?offset=150&limit=150",
        results = listOf(
            PokemonModel(
                name = "bulbasaur",
                url = "https://pokeapi.co/api/v2/pokemon/1/"
            ),
            PokemonModel(
                name = "ivysaur",
                url = "https://pokeapi.co/api/v2/pokemon/2/"
            ),
            PokemonModel(
                name = "venusaur",
                url = "https://pokeapi.co/api/v2/pokemon/3/"
            ),
            PokemonModel(
                name = "charmander",
                url = "https://pokeapi.co/api/v2/pokemon/4/"
            ),
            PokemonModel(
                name = "charmeleon",
                url = "https://pokeapi.co/api/v2/pokemon/5/"
            )
        )
    )

    val pokedex = pokedexModel.toDomain()
    val pokedexEntityList = pokedexModel.results.map { it.toEntity() }
}