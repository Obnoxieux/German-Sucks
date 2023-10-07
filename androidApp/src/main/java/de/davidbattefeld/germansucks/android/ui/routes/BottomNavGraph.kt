package de.davidbattefeld.germansucks.android.ui.routes

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.davidbattefeld.germansucks.android.StatsScreen
import de.davidbattefeld.germansucks.android.ui.FavoritesScreen
import de.davidbattefeld.germansucks.android.ui.MainScreen

@Composable
fun BottomNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    setFabOnClick: (() -> Unit) -> Unit
) {
    Box(modifier = modifier) {
        NavHost(
            navController = navController,
            startDestination = GermanSucksRoute.Main.route
        ){
            composable(route = GermanSucksRoute.Main.route) {
                MainScreen(setFabOnClick = setFabOnClick)
            }
            composable(route = GermanSucksRoute.Favorites.route) {
                FavoritesScreen()
            }
            composable(route = GermanSucksRoute.Stats.route) {
                StatsScreen()
            }
        }
    }
}