package kr.co.diskerr.composefadingedgedemo.home.fadingedge

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import kr.co.diskerr.composefadingedgedemo.component.modifier.EdgeOrientation
import kr.co.diskerr.composefadingedgedemo.databinding.LayoutHorizontalFadingEdgeAndroidViewBinding
import kr.co.diskerr.composefadingedgedemo.databinding.LayoutVerticalFadingEdgeAndroidViewBinding

@Composable
fun FadingEdgeAndroidViewScreen() {
    FadingEdgeDemoLayout(
        title = "Android View",
        brushSelectionEnabled = false
    ) { orientation, _, _ ->
        // Android View follows its background color to draw fading edges, but we can't change this
        // behavior until Android Q.
        // Here, the background is set to transparent, so the final fading edge will look like what
        // you'd get using DstOutFadingEdgeBrush and BlendMode.DstOut in Compose.
        when (orientation) {
            EdgeOrientation.Horizontal -> {
                AndroidViewBinding(factory = LayoutHorizontalFadingEdgeAndroidViewBinding::inflate)
            }
            EdgeOrientation.Vertical -> {
                AndroidViewBinding(factory = LayoutVerticalFadingEdgeAndroidViewBinding::inflate)
            }
        }

    }
}