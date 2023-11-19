package de.davidbattefeld.germansucks.android.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import de.davidbattefeld.germansucks.android.data.GermanSucksApplication
import de.davidbattefeld.germansucks.android.ui.viewmodels.AppViewModel
import de.davidbattefeld.germansucks.android.ui.viewmodels.FavoritesViewModel
import de.davidbattefeld.germansucks.android.ui.viewmodels.MainWordViewModel
import de.davidbattefeld.germansucks.android.ui.viewmodels.StatsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AppViewModel(
                germanSucksApplication(),
                germanSucksApplication().container.wordsRepository,
                germanSucksApplication().container.statsRepository
            )
        }
        initializer {
            MainWordViewModel(
                germanSucksApplication(),
                germanSucksApplication().container.wordsRepository,
                germanSucksApplication().container.statsRepository,
                SavedStateHandle()
            )
        }
        initializer {
            FavoritesViewModel(
                germanSucksApplication(),
                germanSucksApplication().container.statsRepository,
                germanSucksApplication().container.wordsRepository
            )
        }
        initializer {
            StatsViewModel(
                germanSucksApplication(),
                germanSucksApplication().container.wordsRepository,
                germanSucksApplication().container.statsRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [GermanSucksApplication].
 */
fun CreationExtras.germanSucksApplication(): GermanSucksApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GermanSucksApplication)