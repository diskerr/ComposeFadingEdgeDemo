package kr.co.diskerr.composefadingedgedemo.home.fadingedge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.co.diskerr.composefadingedgedemo.component.modifier.horizontalFadingEdge
import kr.co.diskerr.composefadingedgedemo.component.modifier.verticalFadingEdge
import kr.co.diskerr.composefadingedgedemo.component.preview.DummyBox

@Composable
fun FadingEdgeLazyColumnScreen() {
    FadingEdgeDemoLayout(title = "LazyColumn/LazyRow") { isVertical, style, position ->
        if (isVertical) {
            val lazyListState = rememberLazyListState()

            LazyColumn(
                modifier = Modifier
                    .verticalFadingEdge(
                        state = lazyListState,
                        edgeLength = 70.dp,
                        style = style,
                        position = position
                    ),
                state = lazyListState,
                contentPadding = PaddingValues(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    count = 10
                ) {
                    DummyBox(number = it + 1,)
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
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }
    }
}