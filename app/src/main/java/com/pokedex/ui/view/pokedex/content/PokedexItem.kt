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
import com.pokedex.domain.model.Pokemon

@Composable
fun PokedexItem(
    pokemon: Pokemon
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        )
    )
}