package com.pokedex.domain.usecase

import com.pokedex.domain.model.Pokedex
import com.pokedex.domain.repository.PokedexRepository

class GetPokedexUseCase(
    private val repository: PokedexRepository
) {

    companion object {
        private const val LIMIT = 150
    }

    suspend operator fun invoke(page: Int): Result<Pokedex> {
        return repository.getPokedex(limit = LIMIT, offset = page * LIMIT).map { it.toDomain() }
    }
}