package com.pokedex.ui.view.pokedex.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pokedex.domain.model.Pokemon

@Composable
fun PokedexItem(
    modifier: Modifier = Modifier,
    pokemon: Pokemon
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = pokemon.url,
            contentDescription = pokemon.name
        )

        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = pokemon.name
        )

        HorizontalDivider()
    }
}

@Composable
@Preview(showBackground = true)
private fun PokedexItemPreview() {
    PokedexItem(
        pokemon = Pokemon(
            name = "bulbasaur",
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        )
    )
}