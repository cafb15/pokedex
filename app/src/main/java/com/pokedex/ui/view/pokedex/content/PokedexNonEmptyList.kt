package com.pokedex.ui.view.pokedex.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pokedex.domain.model.Pokemon

@Composable
fun PokedexNonEmptyList(
    isPaginating: Boolean,
    pokemons: List<Pokemon>,
    onNextPage: () -> Unit
) {
    val lazyState = rememberLazyListState()
    val shouldPaginate by remember {
        derivedStateOf { lazyState.isScrollInProgress && !lazyState.canScrollForward }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyState
    ) {
        items(pokemons, key = { it.name }) { pokemon ->
            PokedexItem(pokemon = pokemon)
        }

        if (isPaginating) {
            item {
                PaginatingIndicator()
            }
        }
    }

    LaunchedEffect(key1 = shouldPaginate, block = {
        if (shouldPaginate) onNextPage()
    })
}

@Composable
private fun PaginatingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview(showBackground = true)
private fun PokedexNonEmptyListPreview() {
    PokedexNonEmptyList(
        isPaginating = false,
        pokemons = PokedexPreviewParameterProvider.pokemons,
        onNextPage = {}
    )
}