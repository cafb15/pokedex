package com.pokedex.ui.view.pokemondetail.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pokedex.domain.model.PokemonInfo
import com.pokedex.ui.view.components.ExpandableSectionItem
import com.pokedex.ui.view.components.SectionItem

@Composable
fun PokemonDetail(
    pokemonInfo: PokemonInfo
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            model = pokemonInfo.imageUrl,
            contentDescription = pokemonInfo.name
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = pokemonInfo.name
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionItem(
            label = "Tipo de pokemon:",
            textList = pokemonInfo.types.map { it.name }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionItem(
            label = "Habilidades:",
            textList = pokemonInfo.abilities.map { it.name }
        )

        Spacer(modifier = Modifier.height(16.dp))

        SectionItem(
            label = "Habilidades:",
            textList = pokemonInfo.abilities.map { it.name }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExpandableSectionItem(
            title = "Ataques:"
        ) {
            pokemonInfo.attacks.forEach { attack ->
                Text(text = attack.name)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PokemonDetailPreview() {
    PokemonDetail(pokemonInfo = PokemonDetailPreviewParameterProvider.pokemonInfo)
}