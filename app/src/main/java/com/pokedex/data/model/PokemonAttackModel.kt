package com.pokedex.data.model

import com.pokedex.domain.model.PokemonAttack
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonAttackModel(
    @SerialName("move") val attack: AttackModel
) {
    fun toDomain(): PokemonAttack = PokemonAttack(
        name = attack.name
    )
}

@Serializable
data class AttackModel(
    val name: String
)