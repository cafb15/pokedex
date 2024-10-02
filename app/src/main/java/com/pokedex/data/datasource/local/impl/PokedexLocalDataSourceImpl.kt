package com.pokedex.data.datasource.local.impl

import com.pokedex.data.database.PokedexDatabase
import com.pokedex.data.database.entity.PokemonEntity
import com.pokedex.data.datasource.local.PokedexLocalDataSource

class PokedexLocalDataSourceImpl(
    private val pokedexDatabase: PokedexDatabase
) : PokedexLocalDataSource {

    override suspend fun saveFirstPagePokedex(pokemons: List<PokemonEntity>) {
        pokedexDatabase.pokedexDao().insertAll(pokemons)
    }

    override suspend fun getPokemons(): List<PokemonEntity> {
        return pokedexDatabase.pokedexDao().getPokemons()
    }
}