package com.pokedex.data.model

import com.pokedex.domain.model.PokemonAbility
import kotlinx.serialization.Serializable

@Serializable
data class PokemonAbilityModel(
    val ability: AbilityModel
) {
    fun toDomain(): PokemonAbility = PokemonAbility(
        name = ability.name
    )
}

@Serializable
data class AbilityModel(
    val name: String
)