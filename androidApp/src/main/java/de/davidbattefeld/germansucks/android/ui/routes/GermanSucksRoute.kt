package de.davidbattefeld.germansucks.android.ui.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DataExploration
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class GermanSucksRoute(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Main : GermanSucksRoute(
        route = "home",
        title = "Home",
        icon = Icons.Outlined.Home
    )
    object Favorites : GermanSucksRoute(
        route = "favorites",
        title = "Favorites",
        icon = Icons.Outlined.Favorite
    )
    object Stats : GermanSucksRoute(
        route = "stats",
        title = "Stats",
        icon = Icons.Outlined.DataExploration
    )
}