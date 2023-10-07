package de.davidbattefeld.germansucks.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import de.davidbattefeld.germansucks.android.LocalSnackbarHostState
import de.davidbattefeld.germansucks.android.ui.routes.BottomNavGraph
import de.davidbattefeld.germansucks.android.ui.routes.GermanSucksRoute
import ui.NavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GermanSucksApp(
    navController: NavHostController,
) {
    val (fabOnClick, setFabOnClick) = remember { mutableStateOf<(() -> Unit)?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
        val currentRoute = navController
            .currentBackStackEntryFlow
            .collectAsState(initial = navController.currentBackStackEntry)
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        Scaffold(
            topBar = {
                val navTitle = when (currentRoute.value?.destination?.route) {
                    GermanSucksRoute.Main.route -> "German Sucks"
                    GermanSucksRoute.Favorites.route -> "Favorites"
                    GermanSucksRoute.Stats.route -> "Stats"
                    else -> "German Sucks"
                }
                TopAppBar(
                    title = {
                        Text(
                            text = navTitle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {

                    },
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = { NavBar(navController = navController) },
            snackbarHost = {
                SnackbarHost(LocalSnackbarHostState.current) { data ->
                    Snackbar(
                        modifier = Modifier
                            .padding(15.dp)
                    ) {
                        Text(data.visuals.message, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    }
                }
            },
            floatingActionButton = {
                if (currentRoute.value?.destination?.route === GermanSucksRoute.Main.route) {
                    ExtendedFloatingActionButton(
                        onClick = { fabOnClick?.invoke() },
                        expanded = true,
                        icon = { Icon(Icons.Filled.AutoAwesome, "Localized Description") },
                        text = { Text(text = "Load a new random German word") },
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
        ) { padding ->
            BottomNavGraph(
                navController = navController,
                modifier = Modifier.padding(padding),
                setFabOnClick = setFabOnClick,
            )
        }
    }
}