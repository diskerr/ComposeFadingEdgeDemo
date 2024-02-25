package kr.co.diskerr.composefadingedgedemo.component.preview

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.co.diskerr.composefadingedgedemo.component.modifier.EdgePosition
import kr.co.diskerr.composefadingedgedemo.component.modifier.EdgeStyle
import kr.co.diskerr.composefadingedgedemo.component.modifier.horizontalFadingEdge

@Composable
fun DummyBox(
    number: Int,
    style: EdgeStyle,
    modifier: Modifier = Modifier,
    height: Dp = 150.dp
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (number % 2 == 0) {
                    Modifier.background(MaterialTheme.colorScheme.surfaceVariant)
                } else {
                    Modifier
                }
            )
            .padding(vertical = 16.dp)
    ) {
        if (number % 2 == 0) {
            Text(
                text = LoremIpsum.joinToString(" "),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            Text(
                text = "${LoremIpsum[0]}\n${LoremIpsum[1]}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            val icons = listOf(
                Icons.Rounded.Face, Icons.Rounded.Lock, Icons.Rounded.AccountBox,
                Icons.Rounded.AddCircle, Icons.Rounded.Call, Icons.Rounded.Build,
                Icons.Rounded.AccountCircle, Icons.Rounded.DateRange, Icons.Rounded.Home,
            )

            val lazyListState = rememberLazyListState()

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .horizontalFadingEdge(
                        state = lazyListState,
                        edgeLength = 40.dp,
                        style = style,
                        position = EdgePosition.Both
                    ),
                state = lazyListState,
                // userScrollEnabled = false,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    count = Int.MAX_VALUE,
                    key = { it % icons.size }
                ) {
                    val icon = icons[it % icons.size]

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            LaunchedEffect(Unit) {
                while (true) {
                    lazyListState.autoScroll()
                }
            }
        }
    }
}

suspend fun ScrollableState.autoScroll(
    animationSpec: AnimationSpec<Float> = tween(durationMillis = 1000, easing = LinearEasing)
) {
    var previousValue = 0f
    scroll(MutatePriority.Default) {
        animate(0f, 200f, animationSpec = animationSpec) { currentValue, _ ->
            previousValue += scrollBy(currentValue - previousValue)
        }
    }
}

val LoremIpsum = listOf(
    "Lorem ipsum dolor sit amet,",
    "consectetur adipiscing elit,",
    "sed do eiusmod tempor incididunt ut",
    "labore et dolore magna aliqua.",
    "Ut enim ad minim veniam,",
    "quis nostrud exercitation ullamco laboris",
    "nisi ut aliquip ex ea commodo consequat.",
    "Duis aute irure dolor in reprehenderit in",
    "voluptate velit esse cillum dolore eu",
    "fugiat nulla pariatur. Excepteur sint",
    "occaecat cupidatat non proident, sunt",
    "in culpa qui officia deserunt mollit anim",
    "id est laborum."
)