package com.pokedex.di

import com.pokedex.data.api.PokedexApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {

    singleOf(::retrofit)

    single { get<Retrofit>().create(PokedexApi::class.java) }
}

private val jsonConverter = Json { ignoreUnknownKeys = true }

private fun retrofit(): Retrofit = Retrofit.Builder()
    .addConverterFactory(jsonConverter.asConverterFactory("application/json".toMediaType()))
    .baseUrl("https://pokeapi.co/api/v2/")
    .build()