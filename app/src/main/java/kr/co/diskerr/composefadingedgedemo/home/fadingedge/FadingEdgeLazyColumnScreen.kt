package kr.co.diskerr.composefadingedgedemo.home.fadingedge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.co.diskerr.composefadingedgedemo.component.modifier.DefaultHorizontalEdgeStyle
import kr.co.diskerr.composefadingedgedemo.component.modifier.DefaultVerticalEdgeStyle
import kr.co.diskerr.composefadingedgedemo.component.modifier.horizontalFadingEdge
import kr.co.diskerr.composefadingedgedemo.component.modifier.verticalFadingEdge
import kr.co.diskerr.composefadingedgedemo.component.preview.DummyBox
import kr.co.diskerr.composefadingedgedemo.component.preview.LoremIpsum

@Composable
fun FadingEdgeLazyColumnScreen() {
    FadingEdgeDemoLayout(title = "LazyColumn/LazyRow") { isVertical, style, position ->
        if (isVertical) {
            val lazyListState = rememberLazyListState()

            LazyColumn(
                modifier = Modifier
                    .verticalFadingEdge(
                        state = lazyListState,
                        edgeLength = 80.dp,
                        style = style,
                        position = position
                    ),
                state = lazyListState
            ) {
                items(
                    count = 2
                ) {
                    DummyBox(
                        number = it + 1,
                        style = when (style) {
                            DefaultVerticalEdgeStyle -> DefaultHorizontalEdgeStyle
                            else -> HorizontalSrcOverEdgeStyle
                        }
                    )
                }

                items(
                    count = 500
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .background(
                                color = if (it % 2 == 0) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    MaterialTheme.colorScheme.tertiary
                                }
                            )
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "$it ${LoremIpsum[it % LoremIpsum.size]}",
                            color = if (it % 2 == 0) {
                                MaterialTheme.colorScheme.onError
                            } else {
                                MaterialTheme.colorScheme.onTertiary
                            },
                            maxLines = 1
                        )
                    }
                }
            }
        } else {
            val lazyListState = rememberLazyListState()

            LazyRow(
                modifier = Modifier
                    .horizontalFadingEdge(
                        state = lazyListState,
                        edgeLength = 70.dp,
                        style = style,
                        position = position
                    ),
                state = lazyListState,
                contentPadding = PaddingValues(all = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    count = 10
                ) {
                    DummyBox(
                        number = it + 1,
                        style = style,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }
    }
}