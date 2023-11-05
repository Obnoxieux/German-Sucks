package de.davidbattefeld.germansucks.android.ui.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.germansucks.android.ui.AppViewModelProvider
import de.davidbattefeld.germansucks.android.ui.viewmodels.FavoritesViewModel
import de.davidbattefeld.germansucks.shared.classes.SharingMode

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesScreen(
    vm: FavoritesViewModel = viewModel(factory = AppViewModelProvider.Factory),
    setFabOnClick: (() -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val favoriteWords by vm.wordsList.collectAsState(listOf())
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(bottom = 80.dp), // height of FAB
    ) {
        items(
            items = favoriteWords,
            key = { word ->
                word.id
            }
        ) {
            FavoritesListItem(
                word = it,
                copyWord = vm::copyWordToClipboard,
                shareWord = vm::shareWord,
                lookupOnline = vm::lookupWordOnline,
                deleteWord = vm::deleteWordFromFavorites,
                scope = scope,
            )
        }
        item {
            if (favoriteWords.isEmpty()) {
                Text(
                    text = "You haven't saved any words as favorites yet.",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
    LaunchedEffect(Unit) {
        setFabOnClick { vm.shareWord(
            context = context,
            mode = SharingMode.List,
            wordList = favoriteWords
        ) }
    }
}