package de.davidbattefeld.germansucks.android.ui.favorites

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import de.davidbattefeld.germansucks.android.model.Word
import de.davidbattefeld.germansucks.shared.classes.SharingMode
import de.davidbattefeld.germansucks.shared.classes.SharingService
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction1

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FavoritesListItem(
    word: Word,
    copyWord: (word: Word) -> Unit,
    shareWord: (context: Context, mode: SharingMode, wordList: List<Word>) -> Unit,
    lookupOnline: (context: Context, service: SharingService, currentWord: Word) -> Unit,
    deleteWord: KSuspendFunction1<Word, Unit>,
) {
    val scope = rememberCoroutineScope()
    var expanded by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

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
                    .padding(horizontal = 18.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SuggestionChip(
                    onClick = { lookupOnline(context, SharingService.DEEPL, word) },
                    label = { Text("DeepL") }
                )
                SuggestionChip(
                    onClick = { lookupOnline(context, SharingService.GOOGLE_TRANSLATE, word) },
                    label = { Text("G Translate") }
                )
                Spacer(modifier = Modifier.weight(1.0F))
                Row {
                    IconButton(onClick = { copyWord(word) }) {
                        Icon(
                            Icons.Filled.ContentCopy,
                            contentDescription = "Copy word",
                        )
                    }
                    IconButton(onClick = { shareWord(context, SharingMode.SingleWord, listOf(word)) }) {
                        Icon(
                            Icons.Filled.Share,
                            contentDescription = "Share word",
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1.0F))
                IconButton(onClick = {
                    scope.launch {
                        deleteWord(word)
                    }
                }) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "delete word from favorites",
                    )
                }
            }
        }
    }
    Divider()
}