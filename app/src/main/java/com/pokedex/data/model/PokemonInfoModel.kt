package com.pokedex.data.model

import com.pokedex.domain.model.PokemonInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonInfoModel(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeModel>,
    @SerialName("moves") val attacks: List<PokemonAttackModel>,
    val abilities: List<PokemonAbilityModel>
) {
    fun toDomain(): PokemonInfo = PokemonInfo(
        name = name,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
        types = types.map { it.toDomain() },
        attacks = attacks.map { it.toDomain() },
        abilities = abilities.map { it.toDomain() }
    )
}