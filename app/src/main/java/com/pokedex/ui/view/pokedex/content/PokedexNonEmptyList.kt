package com.pokedex.ui.view.pokedex.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pokedex.domain.model.Pokemon
import com.pokedex.core.ui.extensions.applyIf

@Composable
fun PokedexNonEmptyList(
    isPaginating: Boolean,
    pokemons: List<Pokemon>,
    pokemonNameFilter: String,
    onValueChange: (String) -> Unit,
    onNextPage: () -> Unit
) {
    val lazyState = rememberLazyListState()
    val shouldPaginate by remember {
        derivedStateOf { lazyState.isScrollInProgress && !lazyState.canScrollForward }
    }

    Scaffold(
        topBar = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                value = pokemonNameFilter,
                onValueChange = onValueChange
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyState
            ) {
                items(pokemons) { pokemon ->
                    PokedexItem(
                        modifier = Modifier.applyIf(pokemons.last().name == pokemon.name) {
                            padding(bottom = 32.dp)
                        },
                        pokemon = pokemon
                    )
                }
            }

            if (isPaginating) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.BottomCenter),
                    color = Color.Blue
                )
            }
        }
    }

    LaunchedEffect(key1 = shouldPaginate, block = {
        if (shouldPaginate) onNextPage()
    })
}

@Composable
@Preview(showBackground = true)
private fun PokedexNonEmptyListPreview() {
    PokedexNonEmptyList(
        isPaginating = true,
        pokemons = PokedexPreviewParameterProvider.pokemons,
        pokemonNameFilter = "",
        onValueChange = {},
        onNextPage = {}
    )
}