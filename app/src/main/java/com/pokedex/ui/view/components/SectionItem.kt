package com.pokedex.ui.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SectionItem(
    label: String,
    textList: List<String>
) {
    Text(text = label)

    Spacer(modifier = Modifier.height(4.dp))

    textList.forEach {
        Text(text = "- $it")
    }
}

@Composable
@Preview(showBackground = true)
private fun SectionItemPreview() {
    Column {
        SectionItem(
            label = "Abilities",
            textList = listOf("Overgrow", "Chlorophyll")
        )
    }
}