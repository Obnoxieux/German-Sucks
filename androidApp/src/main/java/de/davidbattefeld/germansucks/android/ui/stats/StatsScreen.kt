package de.davidbattefeld.germansucks.android.ui.stats

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.germansucks.android.model.Stats
import de.davidbattefeld.germansucks.android.ui.AppViewModelProvider
import de.davidbattefeld.germansucks.android.ui.viewmodels.StatsViewModel

@Composable
fun StatsScreen(
    vm: StatsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val stats by vm.stats.collectAsState(Stats)
    Text(text = stats.toString())
}