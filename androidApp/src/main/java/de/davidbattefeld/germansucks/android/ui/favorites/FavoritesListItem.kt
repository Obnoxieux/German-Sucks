package de.davidbattefeld.germansucks.android.ui.favorites

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidbattefeld.germansucks.android.model.Word

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FavoritesListItem(
    word: Word
) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        ListItem(
            modifier = Modifier.clickable { expanded = !expanded },
            headlineText = { Text(word.sequence) },
            supportingText = { Text("${word.length} characters") },
            trailingContent = {
                Icon(
                    if (expanded) { Icons.Filled.ExpandLess } else { Icons.Filled.ExpandMore },
                    contentDescription = null
                )
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
        AnimatedVisibility (expanded) {
            Row(
                modifier = Modifier
                    .padding(start = 72.dp)
            ) {
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
        }
    }
    Divider()
}