package com.pokedex.data.model

import com.pokedex.domain.model.PokemonType
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeModel(
    val type: TypeModel
) {
    fun toDomain(): PokemonType = PokemonType(
        name = type.name
    )
}

@Serializable
data class TypeModel(
    val name: String
)