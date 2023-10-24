package de.davidbattefeld.germansucks.android.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.germansucks.android.ui.AppViewModelProvider
import de.davidbattefeld.germansucks.android.ui.viewmodels.FavoritesViewModel
import de.davidbattefeld.germansucks.shared.classes.SharingMode

@Composable
fun FavoritesScreen(
    vm: FavoritesViewModel = viewModel(factory = AppViewModelProvider.Factory),
    setFabOnClick: (() -> Unit) -> Unit,
) {
    val context = LocalContext.current
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = vm.favoriteWords,
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
            )
        }
        item {
            if (vm.favoriteWords.isEmpty()) {
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
            wordList = vm.favoriteWords
        ) }
    }
}