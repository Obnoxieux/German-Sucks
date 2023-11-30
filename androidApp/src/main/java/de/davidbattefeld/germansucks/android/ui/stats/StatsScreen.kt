package de.davidbattefeld.germansucks.android.ui.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.germansucks.android.model.Stats
import de.davidbattefeld.germansucks.android.ui.AppViewModelProvider
import de.davidbattefeld.germansucks.android.ui.viewmodels.StatsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    vm: StatsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val stats by vm.stats.collectAsState(initial = Stats("INITIAL"))
    var showWordCharacterCount by rememberSaveable { mutableStateOf(true) }
    var showLookupCount by rememberSaveable { mutableStateOf(false) }
    var showShareCount by rememberSaveable { mutableStateOf(false) }
    var showMaxWordLength by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(15.dp),
    ) {

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 130.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                FilterChip(
                    onClick = { showWordCharacterCount = !showWordCharacterCount },
                    label = {
                        Text("Character Count")
                    },
                    selected = showWordCharacterCount,
                    leadingIcon = if (showWordCharacterCount) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )
            }
            item {
                FilterChip(
                    onClick = { showLookupCount = !showLookupCount },
                    label = { Text("Lookup Count") },
                    selected = showLookupCount,
                    leadingIcon = if (showLookupCount) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )
            }
            item {
                FilterChip(
                    onClick = { showShareCount = !showShareCount },
                    label = { Text("Share Count") },
                    selected = showShareCount,
                    leadingIcon = if (showShareCount) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )
            }
            item {
                FilterChip(
                    onClick = { showMaxWordLength = !showMaxWordLength },
                    label = { Text("Length Record") },
                    selected = showMaxWordLength,
                    leadingIcon = if (showMaxWordLength) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )
            }
        }
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        LazyColumn(
            //verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                StatsCard(
                    title = "Total Words seen",
                    value = stats?.totalWordsSeen ?: 0,
                    bodytext = "This affects all words that have ever been loaded in the main app section.",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    show = showWordCharacterCount,
                )
            }
            item {
                StatsCard(
                    title = "Total Character length",
                    value = stats?.totalCharactersSeen ?: 0,
                    bodytext = "Imagine the amount of words and sentences in other languages you could" +
                            " construct with this amount of letters!",
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    show = showWordCharacterCount,
                )
            }
            item {
                StatsCard(
                    title = "Longest Word encountered",
                    value = stats?.longestWordLengthDiscovered ?: 0,
                    bodytext = "Can you beat your friends to the longest German word monstrosity?",
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    show = showMaxWordLength,
                )
            }
            item {
                StatsCard(
                    title = "Words looked up",
                    value = stats?.timesClickedLookUp ?: 0,
                    bodytext = "Excuse me this words means WHAT? No shame in getting assistance with the words in this app - so don't worry.",
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    show = showLookupCount,
                )
            }
            item {
                StatsCard(
                    title = "Lookup Percentage",
                    value = stats?.getPercentageLookedUp() ?: 0,
                    bodytext = "How many of the seemingly endless monstrosities did you need help with?",
                    color = MaterialTheme.colorScheme.primaryContainer,
                    show = showLookupCount,
                )
            }
            item {
                StatsCard(
                    title = "Words Shared",
                    value = stats?.totalWordsShared ?: 0,
                    bodytext = "How many times the treasures buried that this app unearthed triggered an immediate urge to notify your friends.",
                    color = MaterialTheme.colorScheme.inverseSurface,
                    show = showShareCount,
                )
            }
        }
    }
}