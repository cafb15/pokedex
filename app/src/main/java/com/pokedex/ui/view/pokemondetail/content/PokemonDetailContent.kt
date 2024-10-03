package com.pokedex.ui.view.pokemondetail.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.pokedex.ui.view.components.PokedexLoading
import com.pokedex.ui.viewmodel.pokemondetail.PokemonDetailViewState

@Composable
fun PokemonDetailContent(
    viewState: PokemonDetailViewState
) {
    when (viewState) {
        PokemonDetailViewState.Loading -> PokedexLoading()
        is PokemonDetailViewState.Success -> PokemonDetail(pokemonInfo = viewState.pokemonInfo)
    }
}

@Composable
@Preview(showBackground = true)
private fun PokemonDetailContentPreview(
    @PreviewParameter(PokemonDetailPreviewParameterProvider::class) viewState: PokemonDetailViewState
) {
    PokemonDetailContent(viewState = viewState)
}