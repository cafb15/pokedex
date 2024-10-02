package com.pokedex.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pokedex.domain.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val url: String
) {
    fun toDomain(): Pokemon = Pokemon(
        name = name,
        url = url
    )
}
