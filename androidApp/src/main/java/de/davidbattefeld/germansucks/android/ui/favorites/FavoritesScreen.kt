package de.davidbattefeld.germansucks.android.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.germansucks.android.model.Word
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
        //items()
        for (i in 1 until 12) {
            item {
                FavoritesListItem(Word(sequence = "Eyjafjallajökull"))
            }
        }
    }
    //TODO: share whole list with FAB
}