package kr.co.diskerr.composefadingedgedemo.component.modifier

import android.view.View
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.co.diskerr.composefadingedgedemo.component.preview.DummyBox
import kr.co.diskerr.composefadingedgedemo.ui.theme.ComposeFadingEdgeDemoTheme
import kotlin.math.absoluteValue
import kotlin.math.min

/**
 * Modify element to include vertical fading edges for a vertically scrollable container.
 * The default look and behavior is identical to that of [View]'s fading edge.
 *
 * It must be applied after [Modifier.verticalScroll].
 */
fun Modifier.verticalFadingEdge(
    state: ScrollState,
    edgeLength: Dp,
    style: EdgeStyle = DefaultVerticalEdgeStyle,
    position: EdgePosition = EdgePosition.Both
) = this.then(
    Modifier
        .fadingEdge(
            state = state,
            edgeLength = edgeLength,
            isVertical = true,
            style = style,
            position = position
        )
)

/**
 * Modify element to include horizontal fading edges for LazyColumn.
 *
 * The default look and behavior is identical to that of [View]'s fading edge.
 *
 * Limitation:
 * This only works correctly if the width of the first and last item (including content padding,
 * if any) is equal to or greater than [edgeLength]. Otherwise, the edge will suddenly shorten
 * when the first or last item starts to appear. This occurs because LazyLists cannot measure
 * the total scroll area size in advance due to their lazy nature, unlike scrollable Column or Row.
 * Additionally, the measured sizes of items that are not yet visible (i.e., prefetched items)
 * are not accessible outside of LazyLists.
 *
 * @param style The style of the fading edge. Use the default value if the background color of
 *  the composable, to which this modifier will be applied, is transparent or if blending with
 *  the parent composable's background color is desired.
 *  If a solid background color is specified for the composable, you should use a style returned by
 *  the [EdgeStyle.createViewCompatibleStyle] method. This mirrors the default behavior of [View]
 *  when a solid background color is set for a view.
 */
fun Modifier.verticalFadingEdge(
    state: LazyListState,
    edgeLength: Dp,
    style: EdgeStyle = DefaultVerticalEdgeStyle,
    position: EdgePosition = EdgePosition.Both
) = this.then(
    Modifier
        .fadingEdge(
            state = state,
            edgeLength = edgeLength,
            isVertical = true,
            style = style,
            position = position
        )
)

/**
 * Modify element to include horizontal fading edges for a horizontally scrollable container.
 *
 * The default look and behavior is identical to that of [View]'s fading edge.
 *
 * Limitation:
 * This only works correctly if the width of the first and last item (including content padding,
 * if any) is equal to or greater than [edgeLength]. Otherwise, the edge will suddenly shorten
 * when the first or last item starts to appear. This occurs because LazyLists cannot measure
 * the total scroll area size in advance due to their lazy nature, unlike scrollable Column or Row.
 * Additionally, the measured sizes of items that are not yet visible (i.e., prefetched items)
 * are not accessible outside of LazyLists.
 *
 * @param style The style of the fading edge. Use the default value if the background color of
 *  the composable, to which this modifier will be applied, is transparent or if blending with
 *  the parent composable's background color is desired.
 *  If a solid background color is specified for the composable, you should use a style returned by
 *  the [EdgeStyle.createViewCompatibleStyle] method. This mirrors the default behavior of [View]
 *  when a solid background color is set for a view.
 */
fun Modifier.horizontalFadingEdge(
    state: ScrollState,
    edgeLength: Dp,
    style: EdgeStyle = DefaultHorizontalEdgeStyle,
    position: EdgePosition = EdgePosition.Both
) = this.then(
    Modifier
        .fadingEdge(
            state = state,
            edgeLength = edgeLength,
            isVertical = false,
            style = style,
            position = position
        )
)

/**
 * Modify element to include horizontal fading edges for LazyRow.
 *
 * The default look and behavior is identical to that of [View]'s fading edge.
 * Refer to [verticalFadingEdge] for a detailed explanation.
 */
fun Modifier.horizontalFadingEdge(
    state: LazyListState,
    edgeLength: Dp,
    style: EdgeStyle = DefaultHorizontalEdgeStyle,
    position: EdgePosition = EdgePosition.Both
) = this.then(
    Modifier
        .fadingEdge(
            state = state,
            edgeLength = edgeLength,
            isVertical = false,
            style = style,
            position = position
        )
)

fun Modifier.fadingEdge(
    state: ScrollState,
    edgeLength: Dp,
    isVertical: Boolean,
    style: EdgeStyle,
    position: EdgePosition
) = this.then(
    Modifier
        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithContent {
            drawContent()

            val edgeLengthPx = edgeLength.toPx()
            val scrollValue = state.value.toFloat()
            val scrollMaxValue = state.maxValue.toFloat()
            val size = if (isVertical) size.height else size.width

            if (position.includes(EdgePosition.Start) && state.canScrollBackward) {
                drawFadingEdge(
                    startInset = scrollValue,
                    endInset = size - scrollValue - min(edgeLengthPx, scrollValue),
                    style = style,
                    isVertical = isVertical
                )
            }

            if (position.includes(EdgePosition.End) && state.canScrollForward) {
                val remainingScroll = scrollMaxValue - scrollValue
                drawFadingEdge(
                    startInset = size - remainingScroll - min(edgeLengthPx, remainingScroll),
                    endInset = remainingScroll,
                    isVertical = isVertical,
                    style = style,
                    reverse = true
                )
            }
        }
)

fun Modifier.fadingEdge(
    state: LazyListState,
    edgeLength: Dp,
    isVertical: Boolean,
    style: EdgeStyle,
    position: EdgePosition
) = this.then(
    Modifier
        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithContent {
            drawContent()

            val edgeLengthPx = edgeLength.toPx()
            val size = if (isVertical) size.height else size.width

            if (position.includes(EdgePosition.Start) && state.canScrollBackward) {
                val passedScroll = with(state.layoutInfo) {
                    visibleItemsInfo
                        .firstOrNull { it.index == 0 }
                        ?.offset
                        ?.absoluteValue
                        ?.toFloat()
                        ?: Float.MAX_VALUE
                }

                drawFadingEdge(
                    startInset = 0f,
                    endInset = size - min(edgeLengthPx, passedScroll),
                    isVertical = isVertical,
                    style = style
                )
            }

            if (position.includes(EdgePosition.End) && state.canScrollForward) {
                val remainingScroll = with(state.layoutInfo) {
                    visibleItemsInfo
                        .lastOrNull { it.index == totalItemsCount - 1 }
                        ?.let { it.size + it.offset + afterContentPadding - viewportEndOffset }
                        ?.toFloat()
                        ?: Float.MAX_VALUE
                }

                drawFadingEdge(
                    startInset = size - min(edgeLengthPx, remainingScroll),
                    endInset = 0f,
                    isVertical = isVertical,
                    style = style,
                    reverse = true
                )
            }
        }
)

private fun DrawScope.drawFadingEdge(
    startInset: Float,
    endInset: Float,
    isVertical: Boolean,
    style: EdgeStyle,
    reverse: Boolean = false
) {
    var left = 0f
    var top = 0f
    var right = 0f
    var bottom = 0f

    if (isVertical) {
        top = startInset
        bottom = endInset
    } else {
        left = startInset
        right = endInset
    }

    inset(left = left, top = top, right = right, bottom = bottom) {
        rotate(if (reverse) 180f else 0f) {
            drawRect(brush = style.brush, blendMode = style.blendMode)
        }
    }
}

@Immutable
enum class EdgePosition {
    Start, End, Both;

    fun includes(other: EdgePosition): Boolean = (this == Both || this == other)
}

@Immutable
data class EdgeStyle(
    val brush: Brush,
    val blendMode: BlendMode
) {
    companion object {
        /**
         * Creates a fading edge style that is exactly the same as [android.view.View]'s
         * fading edge, when a solid background color is specified for the View.
         *
         * @param color The solid background color of a view on which it will be drawn.
         */
        fun createViewCompatibleStyle(
            color: Color,
            isVertical: Boolean
        ): EdgeStyle {
            val colorStops = arrayOf(
                0f to color.copy(1f),
                1f to color.copy(0f),
            )

            return EdgeStyle(
                brush = if (isVertical) {
                    Brush.verticalGradient(*colorStops)
                } else {
                    Brush.horizontalGradient(*colorStops)
                },
                blendMode = BlendMode.SrcOver
            )
        }
    }
}

val DefaultHorizontalEdgeStyle = EdgeStyle(
    brush = Brush.horizontalGradient(
        0f to Color(0xFF000000),
        1f to Color(0x00000000)
    ),
    blendMode = BlendMode.DstOut
)

val DefaultVerticalEdgeStyle = EdgeStyle(
    brush = Brush.verticalGradient(
        0f to Color(0xFF000000),
        1f to Color(0x00000000)
    ),
    blendMode = BlendMode.DstOut
)

@Preview(showBackground = true)
@Composable
private fun ColumnFadingEdgePreview() {
    ComposeFadingEdgeDemoTheme {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .height(500.dp)
                .verticalScroll(state = scrollState)
                .verticalFadingEdge(state = scrollState, edgeLength = 70.dp)
                .padding(horizontal = 40.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            (1..10).forEach { DummyBox(number = it, style = DefaultVerticalEdgeStyle) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LazyColumnFadingEdgePreview() {
    ComposeFadingEdgeDemoTheme {
        val lazyListState = rememberLazyListState()

        LazyColumn(
            modifier = Modifier
                .height(500.dp)
                .verticalFadingEdge(state = lazyListState, edgeLength = 70.dp),
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 40.dp, vertical = 16.dp)
        ) {
            items(
                count = 10
            ) {
                DummyBox(number = it + 1, style = DefaultVerticalEdgeStyle)
            }
        }
    }
}
