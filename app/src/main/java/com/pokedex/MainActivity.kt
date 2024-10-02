package com.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pokedex.ui.navigation.LocalNavController
import com.pokedex.ui.navigation.PokedexRoute
import com.pokedex.ui.navigation.pokedexDestination
import com.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val navController = rememberNavController()

                CompositionLocalProvider(
                    LocalNavController provides navController
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        NavHost(
                            navController = navController,
                            startDestination = PokedexRoute
                        ) {
                            pokedexDestination()
                        }
                    }
                }
            }
        }
    }
}
