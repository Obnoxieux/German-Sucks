package de.davidbattefeld.germansucks.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf

class MainWordViewModel(application: Application) : GenericViewModel(application) {
    var currentWord = mutableStateOf("No word loaded")
}