package com.pokedex.data.model

import com.pokedex.data.database.entity.PokemonEntity
import com.pokedex.domain.model.Pokedex
import com.pokedex.domain.model.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokedexModel(
    val count: Int,
    val next: String?,
    val results: List<PokemonModel>
) {
    fun toDomain(): Pokedex = Pokedex(
        isLastPage = next == null,
        pokemons = results.map { it.toDomain() }
    )
}

@Serializable
data class PokemonModel(
    val name: String,
    val url: String
) {
    fun toEntity(): PokemonEntity = PokemonEntity(
        name = name,
        url = url
    )

    fun toDomain(): Pokemon = Pokemon(
        name = name,
        url = url
    )
}