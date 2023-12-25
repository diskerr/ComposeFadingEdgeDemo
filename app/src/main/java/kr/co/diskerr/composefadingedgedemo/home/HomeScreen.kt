package kr.co.diskerr.composefadingedgedemo.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeRoute(
    onColumnClick: () -> Unit,
    onLazyColumnClick: () -> Unit,
    onAndroidViewClick: () -> Unit
) {
    HomeScreen(
        onColumnClick = onColumnClick,
        onLazyColumnClick = onLazyColumnClick,
        onAndroidViewClick = onAndroidViewClick
    )
}

@Composable
fun HomeScreen(
    onColumnClick: () -> Unit,
    onLazyColumnClick: () -> Unit,
    onAndroidViewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(all = 16.dp)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FadingEdgeDemo(
            onColumnClick = onColumnClick,
            onLazyColumnClick = onLazyColumnClick,
            onAndroidViewClick = onAndroidViewClick
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FadingEdgeDemo(
    onColumnClick: () -> Unit,
    onLazyColumnClick: () -> Unit,
    onAndroidViewClick: () -> Unit
) {
    Text(
        text = "Modifier.fadingEdge()",
        style = MaterialTheme.typography.bodyLarge
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextButton(
            onClick = onColumnClick
        ) {
            Text("Column/Row")
        }

        TextButton(
            onClick = onLazyColumnClick
        ) {
            Text("LazyColumn/LazyRow")
        }

        TextButton(
            onClick = onAndroidViewClick
        ) {
            Text("Android View")
        }
    }
}