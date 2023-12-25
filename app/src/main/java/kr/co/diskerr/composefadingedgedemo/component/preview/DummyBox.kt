package kr.co.diskerr.composefadingedgedemo.component.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DummyBox(
    number: Int,
    modifier: Modifier = Modifier,
    height: Dp = 150.dp
) {
    val testColors = remember { listOf(Color.Red, Color.Green, Color.Blue) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(testColors[number % testColors.size]),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$number",
            style = MaterialTheme.typography.displayLarge
        )
    }
}
