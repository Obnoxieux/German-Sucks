package de.davidbattefeld.germansucks.android.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.davidbattefeld.germansucks.android.ui.theme.GermanSucksTheme
import de.davidbattefeld.germansucks.android.ui.viewmodels.MainWordViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainWordViewModel: MainWordViewModel = viewModel(),
    setFabOnClick: (() -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        HeaderCard()
        MainWordCard(
            word = mainWordViewModel.currentWord.value,
            scope = scope
        )
        ShareCard()
    }
    LaunchedEffect(Unit) {
        setFabOnClick { mainWordViewModel.setCurrentWord() }
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
fun MainScreenPreview() {
    GermanSucksTheme {
        //MainScreen({})
    }
}