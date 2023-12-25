package kr.co.diskerr.composefadingedgedemo.home.fadingedge

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kr.co.diskerr.composefadingedgedemo.component.modifier.EdgeOrientation
import kr.co.diskerr.composefadingedgedemo.component.modifier.horizontalFadingEdge
import kr.co.diskerr.composefadingedgedemo.component.modifier.verticalFadingEdge
import kr.co.diskerr.composefadingedgedemo.component.preview.DummyBox

@Composable
fun FadingEdgeColumnScreen() {
    FadingEdgeDemoLayout(title = "Column/Row") { orientation, style, position ->
        when (orientation) {
            EdgeOrientation.Horizontal -> {
                val scrollState = rememberScrollState()

                Row(
                    modifier = Modifier
                        .horizontalScroll(state = scrollState)
                        .horizontalFadingEdge(
                            state = scrollState,
                            edgeLength = 70.dp,
                            style = style,
                            position = position
                        )
                        .padding(all = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    (1..10).forEach {
                        DummyBox(
                            number = it,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }
            EdgeOrientation.Vertical -> {
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .verticalScroll(state = scrollState)
                        .verticalFadingEdge(
                            state = scrollState,
                            edgeLength = 70.dp,
                            style = style,
                            position = position
                        )
                        .padding(all = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    (1..10).forEach { DummyBox(number = it) }
                }
            }
        }
    }
}