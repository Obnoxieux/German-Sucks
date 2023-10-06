package de.davidbattefeld.germansucks.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import de.davidbattefeld.germansucks.android.ui.routes.BottomNavGraph
import ui.NavBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GermanSucksApp(navHostController: NavHostController) {
    Scaffold(
        bottomBar = { NavBar(navController = navHostController) }
    ) { padding ->
        BottomNavGraph(navController = navHostController, modifier = Modifier.padding(padding))
    }
}