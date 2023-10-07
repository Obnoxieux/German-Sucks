package de.davidbattefeld.germansucks.android.ui.favorites

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.davidbattefeld.germansucks.shared.model.Word

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FavoritesListItem(
    word: Word
) {
    ListItem(
        headlineText = { Text(word.sequence) },
        supportingText = { Text("${word.length} characters") },
        trailingContent = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.Share,
                    contentDescription = "Share word",
                )
            }
        },
        leadingContent = {
            WordAvatar(
                character = word.firstCharacter,
                circleColor = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    )
    Divider()
}