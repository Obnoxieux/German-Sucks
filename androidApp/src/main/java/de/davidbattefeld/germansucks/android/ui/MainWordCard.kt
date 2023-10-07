package de.davidbattefeld.germansucks.android.ui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PsychologyAlt
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.germansucks.android.LocalSnackbarHostState
import de.davidbattefeld.germansucks.android.ui.theme.GermanSucksTheme
import de.davidbattefeld.germansucks.android.ui.viewmodels.MainWordViewModel
import de.davidbattefeld.germansucks.shared.classes.SharingService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainWordCard(
    word: String,
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
            Row() {
                Icon(imageVector = Icons.Filled.MenuBook, contentDescription = null)
                Spacer(modifier = Modifier.weight(1.0F))
                Icon(imageVector = Icons.Filled.PsychologyAlt, contentDescription = null)
            }
            Text("Your word is...")
            Text(
                word,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            )
            Text("Action:", style = MaterialTheme.typography.labelLarge)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { vm.lookupWordOnline(
                    context = context,
                    service = SharingService.DEEPL
                    )
                }) { Text("DeepL") }
                Button(onClick = { vm.lookupWordOnline(
                    context = context,
                    service = SharingService.GOOGLE_TRANSLATE)
                }) { Text("G Translate") }
                OutlinedButton(
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Copied to clipboard!")
                        }
                        vm.copyWordToClipboard()
                    },
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimaryContainer)
                ) { Text("Copy") }
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
