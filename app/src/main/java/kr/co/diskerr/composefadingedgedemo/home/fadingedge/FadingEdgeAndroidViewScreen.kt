package kr.co.diskerr.composefadingedgedemo.home.fadingedge

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import kr.co.diskerr.composefadingedgedemo.databinding.LayoutHorizontalFadingEdgeAndroidViewBinding
import kr.co.diskerr.composefadingedgedemo.databinding.LayoutVerticalFadingEdgeAndroidViewBinding

@Composable
fun FadingEdgeAndroidViewScreen() {
    FadingEdgeDemoLayout(
        title = "Android View",
        brushSelectionEnabled = false
    ) { isVertical, _, _ ->
        // Android Views use a DstOut linear gradient brush to draw fading edges.
        // Can't change this at runtime without messing with [View.getSolidColor],
        // so there's no SrcOver brush support in Android VieFFw mode.
        // But, it's okay because if the solid color and the background color are the same,
        // the fading edge with the SrcOver will look just like it does with the DstOut.
        if (isVertical) {
            AndroidViewBinding(factory = LayoutVerticalFadingEdgeAndroidViewBinding::inflate)
        } else {
            AndroidViewBinding(factory = LayoutHorizontalFadingEdgeAndroidViewBinding::inflate)
        }
    }
}