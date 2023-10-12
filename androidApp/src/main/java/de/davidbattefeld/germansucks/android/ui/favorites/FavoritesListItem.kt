package de.davidbattefeld.germansucks.android.ui.favorites

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.davidbattefeld.germansucks.android.model.Word

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FavoritesListItem(
    word: Word
) {
    ListItem(
        headlineText = { Text(word.sequence) },
        supportingText = { Text("${word.length} characters") },
        trailingContent = {
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Filled.ContentCopy,
                        contentDescription = "Copy word",
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Filled.Share,
                        contentDescription = "Share word",
                    )
                }
            }
        },
        leadingContent = {
            val colors = when {
                word.length < Word.WORD_LENGTH_SHORT -> Pair(MaterialTheme.colorScheme.tertiary, MaterialTheme.colorScheme.onTertiary)
                word.length in Word.WORD_LENGTH_SHORT..Word.WORD_LENGTH_MEDIUM -> Pair(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.onSecondary)
                else -> Pair(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)
            }
            WordAvatar(
                character = word.firstCharacter,
                circleColor = colors.first,
                textColor = colors.second
            )
        }
    )
    Divider()
}