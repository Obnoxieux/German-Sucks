package de.davidbattefeld.germansucks.android.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.germansucks.android.ui.theme.GermanSucksTheme
import de.davidbattefeld.germansucks.android.ui.viewmodels.MainWordViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainWordViewModel: MainWordViewModel = viewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val snackbarHostState = remember { SnackbarHostState() }
    var selectedNavItem by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Text(data.visuals.message, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { mainWordViewModel.setCurrentWord() },
                expanded = true,
                icon = { Icon(Icons.Filled.AutoAwesome, "Localized Description") },
                text = { Text(text = "Load a new random German word") },
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "German Sucks",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {

                },
                actions = {
                    /*IconButton(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Heart button clicked")
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }*/
                },
                scrollBehavior = scrollBehavior
            )

        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                HeaderCard()
                MainWordCard(
                    word = mainWordViewModel.currentWord.value,
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
                ShareCard()
            }
        }
    )
}

@Preview(
    showBackground = true,
    widthDp = 400,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(
    showBackground = true,
    widthDp = 400
)
@Composable
fun MainScreenPreview() {
    GermanSucksTheme {
        MainScreen()
    }
}