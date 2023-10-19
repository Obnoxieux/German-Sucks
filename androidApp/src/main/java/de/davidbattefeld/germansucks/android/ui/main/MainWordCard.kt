package de.davidbattefeld.germansucks.android.ui.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PsychologyAlt
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.germansucks.android.LocalSnackbarHostState
import de.davidbattefeld.germansucks.android.model.Word
import de.davidbattefeld.germansucks.android.ui.theme.GermanSucksTheme
import de.davidbattefeld.germansucks.android.ui.viewmodels.MainWordViewModel
import de.davidbattefeld.germansucks.shared.classes.SharingService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainWordCard(
    word: Word,
    scope: CoroutineScope,
) {
    val snackbarHostState = LocalSnackbarHostState.current
    val context = LocalContext.current
    val vm = viewModel<MainWordViewModel>()
    ElevatedCard(
        modifier = Modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.MenuBook, contentDescription = null)
                Text("Your word is...",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 12.dp))
                Spacer(modifier = Modifier.weight(1.0F))
                Icon(imageVector = Icons.Filled.PsychologyAlt, contentDescription = null)
            }
            Text(
                word.sequence,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
            Text("Translate:", style = MaterialTheme.typography.labelLarge)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(onClick = { vm.lookupWordOnline(
                    context = context,
                    service = SharingService.DEEPL,
                    currentWord = vm.currentWord.value.sequence
                    )
                }) { Text("DeepL") }
                OutlinedButton(onClick = { vm.lookupWordOnline(
                    context = context,
                    service = SharingService.GOOGLE_TRANSLATE,
                    currentWord = vm.currentWord.value.sequence
                    )
                }) { Text("G Translate") }
            }
            Text("Actions:", style = MaterialTheme.typography.labelLarge)
            Row {
                Button(
                    onClick = {
                    scope.launch {
                        vm.saveWordToFavorites(vm.currentWord.value)
                    }
                }) {
                    Icon(Icons.Filled.Favorite, contentDescription = "heart")
                    Text("Add to Favorites", modifier = Modifier.padding(start = 6.dp))
                }
                Spacer(modifier = Modifier.weight(1.0F))
                IconButton(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Copied to clipboard!")
                        }
                        vm.copyWordToClipboard(vm.currentWord.value)
                    },
                ) { Icon(Icons.Filled.ContentCopy, contentDescription = "copy word to clipboard") }
            }
        }
    }
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
fun MainWordCardPreview() {
    GermanSucksTheme {
        //MainWordCard(word = "Inhaberschuldverschreibung")
    }
}
