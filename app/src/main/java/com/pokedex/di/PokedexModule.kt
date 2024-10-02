package com.pokedex.di

import com.pokedex.data.datasource.remote.PokedexRemoteDataSource
import com.pokedex.data.datasource.remote.impl.PokedexRemoteDataSourceImpl
import com.pokedex.data.repository.PokedexRepositoryImpl
import com.pokedex.domain.repository.PokedexRepository
import com.pokedex.domain.usecase.GetPokedexUseCase
import com.pokedex.ui.viewmodel.pokedex.PokedexViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val pokedexModule = module {

    factory<PokedexRemoteDataSource> { PokedexRemoteDataSourceImpl(api = get()) }

    factory<PokedexRepository> { PokedexRepositoryImpl(remoteDataSource = get()) }

    factory { GetPokedexUseCase(repository = get()) }

    viewModel { PokedexViewModel(getPokedexUseCase = get()) }
}