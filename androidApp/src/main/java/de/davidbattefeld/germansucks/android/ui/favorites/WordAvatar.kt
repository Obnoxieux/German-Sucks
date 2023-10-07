package de.davidbattefeld.germansucks.android.ui.favorites

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WordAvatar(character: String, circleColor: Color, textColor: Color) {
    Text(
        text = character,
        style = MaterialTheme.typography.titleLarge,
        color = textColor,
        modifier = Modifier
            .padding(16.dp)
            .drawBehind {
            drawCircle(
                color = circleColor,
                radius = (this.size.maxDimension * 0.75).toFloat()
            )
        }
    )
}