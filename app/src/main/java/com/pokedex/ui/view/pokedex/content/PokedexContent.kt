package com.pokedex.ui.view.pokedex.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.pokedex.ui.viewmodel.pokedex.PokedexViewState

@Composable
fun PokedexContent(
    viewState: PokedexViewState,
    onValueChange: (String) -> Unit,
    onNextPage: () -> Unit
) {
    when (viewState) {
        PokedexViewState.Loading -> PokedexLoading()
        is PokedexViewState.Success -> PokedexNonEmptyList(
            isPaginating = viewState.isPaginating,
            pokemons = viewState.pokemons,
            pokemonNameFilter = viewState.pokemonNameFilter,
            onValueChange = onValueChange,
            onNextPage = onNextPage
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PokedexContentPreview(
    @PreviewParameter(PokedexPreviewParameterProvider::class) viewState: PokedexViewState
) {
    PokedexContent(
        viewState = viewState,
        onValueChange = {},
        onNextPage = {}
    )
}