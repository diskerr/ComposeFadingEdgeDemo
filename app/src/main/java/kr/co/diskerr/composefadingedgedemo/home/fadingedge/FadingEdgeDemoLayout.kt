package kr.co.diskerr.composefadingedgedemo.home.fadingedge

import androidx.compose.foundation.gestures.Orientation
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
import kr.co.diskerr.composefadingedgedemo.component.modifier.EdgePosition
import kr.co.diskerr.composefadingedgedemo.component.modifier.EdgeStyle

@Composable
fun FadingEdgeDemoLayout(
    title: String,
    brushSelectionEnabled: Boolean = true,
    content: @Composable (Boolean, EdgeStyle, EdgePosition) -> Unit
) {
    var isVertical by remember { mutableStateOf(true) }
    var demoEdgeStyle by remember { mutableStateOf(DemoEdgeStyle.DefaultDstOut) }
    val style by remember {
        derivedStateOf { getMatchingEdgeStyle(isVertical = isVertical, style = demoEdgeStyle) }
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
        ) {
            content(isVertical, style, position)
        }

        BottomOptionBar(
            onOrientationChanged = { isVertical = it },
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
    onOrientationChanged: (Boolean) -> Unit,
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

            Orientation.entries.forEach {
                TextButton(
                    onClick = { onOrientationChanged(it == Orientation.Vertical) }
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

/* private */ val VerticalSrcOverEdgeStyle =
    EdgeStyle.createViewCompatibleStyle(color = Color(0xFFFAF9FD), isVertical = true)

/* private */ val HorizontalSrcOverEdgeStyle =
    EdgeStyle.createViewCompatibleStyle(color = Color(0xFFFAF9FD), isVertical = false)

private fun getMatchingEdgeStyle(isVertical: Boolean, style: DemoEdgeStyle): EdgeStyle {
    return when (style) {
        DemoEdgeStyle.DefaultDstOut -> {
            if (isVertical) DefaultVerticalEdgeStyle else DefaultHorizontalEdgeStyle
        }

        DemoEdgeStyle.SrcOver -> {
            if (isVertical) VerticalSrcOverEdgeStyle else HorizontalSrcOverEdgeStyle
        }
    }
}