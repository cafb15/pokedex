package com.pokedex.di

import com.pokedex.data.datasource.remote.impl.PokedexRemoteDataSourceImpl
import com.pokedex.data.repository.PokedexRepositoryImpl
import com.pokedex.domain.repository.PokedexRepository
import com.pokedex.domain.usecase.GetPokedexUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val pokedexModule = module {

    factoryOf(::PokedexRemoteDataSourceImpl)

    factory<PokedexRepository> {
        PokedexRepositoryImpl(remoteDataSource = get())
    }

    factoryOf(::GetPokedexUseCase)
}