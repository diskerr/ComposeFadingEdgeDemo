package kr.co.diskerr.composefadingedgedemo.home.fadingedge

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.co.diskerr.composefadingedgedemo.component.modifier.DefaultHorizontalEdgeStyle
import kr.co.diskerr.composefadingedgedemo.component.modifier.DefaultVerticalEdgeStyle
import kr.co.diskerr.composefadingedgedemo.component.modifier.EdgeOrientation
import kr.co.diskerr.composefadingedgedemo.component.modifier.EdgePosition
import kr.co.diskerr.composefadingedgedemo.component.modifier.EdgeStyle

@Composable
fun FadingEdgeDemoLayout(
    title: String,
    brushSelectionEnabled: Boolean = true,
    content: @Composable (EdgeOrientation, EdgeStyle, EdgePosition) -> Unit
) {
    var orientation by remember { mutableStateOf(EdgeOrientation.Vertical) }
    var demoEdgeStyle by remember { mutableStateOf(DemoEdgeStyle.DefaultDstOut) }
    val style by remember {
        derivedStateOf { getMatchingEdgeStyle(orientation = orientation, style = demoEdgeStyle) }
    }

    var position by remember { mutableStateOf(EdgePosition.Both) }

    Column(
        modifier = Modifier.padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )

        Box(
            modifier = Modifier
                .weight(weight = 1f, fill = false)
                .border(width = 1.dp, color = Color.Black)
        ) {
            content(orientation, style, position)
        }

        BottomOptionBar(
            onOrientationChanged = { orientation = it },
            onStyleChanged = { demoEdgeStyle = it },
            onPositionChanged = { position = it },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End),
            enabled = brushSelectionEnabled
        )
    }
}

@Immutable
enum class DemoEdgeStyle(val label: String) {
    DefaultDstOut("Default (DstOut)"),
    SrcOver("SrcOver")
}

@Composable
private fun BottomOptionBar(
    onOrientationChanged: (EdgeOrientation) -> Unit,
    onStyleChanged: (DemoEdgeStyle) -> Unit,
    onPositionChanged: (EdgePosition) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Orientation")

            EdgeOrientation.entries.forEach {
                TextButton(
                    onClick = { onOrientationChanged(it) }
                ) {
                    Text(text = it.name)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Style")

            DemoEdgeStyle.entries.forEach {
                TextButton(
                    onClick = { onStyleChanged(it) },
                    enabled = enabled
                ) {
                    Text(text = it.label)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Position")

            EdgePosition.entries.forEach {
                TextButton(
                    onClick = { onPositionChanged(it) },
                    enabled = enabled
                ) {
                    Text(text = it.name)
                }
            }
        }
    }
}

private val HorizontalSrcOverEdgeStyle =
    EdgeStyle.createViewCompatibleStyle(Color.Black, EdgeOrientation.Horizontal)

private val VerticalSrcOverEdgeStyle =
    EdgeStyle.createViewCompatibleStyle(Color.Black, EdgeOrientation.Vertical)

private fun getMatchingEdgeStyle(orientation: EdgeOrientation, style: DemoEdgeStyle): EdgeStyle {
    return when (style) {
        DemoEdgeStyle.DefaultDstOut -> {
            when (orientation) {
                EdgeOrientation.Horizontal -> DefaultHorizontalEdgeStyle
                EdgeOrientation.Vertical -> DefaultVerticalEdgeStyle
            }
        }
        DemoEdgeStyle.SrcOver -> {
            when (orientation) {
                EdgeOrientation.Horizontal -> HorizontalSrcOverEdgeStyle
                EdgeOrientation.Vertical -> VerticalSrcOverEdgeStyle
            }
        }
    }
}