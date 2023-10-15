package de.davidbattefeld.germansucks.android.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.germansucks.android.ui.AppViewModelProvider
import de.davidbattefeld.germansucks.android.ui.viewmodels.FavoritesViewModel

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    favoritesViewModel: FavoritesViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = favoritesViewModel.favoriteWords,
            key = { word ->
                word.id
            }
        ) {
            FavoritesListItem(word = it)
        }
        item {
            if (favoritesViewModel.favoriteWords.isEmpty()) {
                Text(
                    text = "You haven't saved any words as favorites yet.",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
    //TODO: share whole list with FAB
}