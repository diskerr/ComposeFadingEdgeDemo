package kr.co.diskerr.composefadingedgedemo.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kr.co.diskerr.composefadingedgedemo.home.fadingedge.FadingEdgeAndroidViewScreen
import kr.co.diskerr.composefadingedgedemo.home.fadingedge.FadingEdgeColumnScreen
import kr.co.diskerr.composefadingedgedemo.home.fadingedge.FadingEdgeLazyColumnScreen

const val HomeGraph = "home_graph"
const val HomeRoute = "home_route"
const val FadingEdgeColumnRoute = "fading_edge_column_route"
const val FadingEdgeLazyColumnRoute = "fading_edge_lazy_column_route"
const val FadingEdgeAndroidViewRoute = "fading_edge_android_view_route"

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        startDestination = HomeRoute,
        route = HomeGraph
    ) {
        homeRoute(navController)
        fadingEdgeColumnRoute()
        fadingEdgeLazyColumnRoute()
        fadingEdgeAndroidViewRoute()
    }
}

fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(route = HomeRoute) {
        HomeRoute(
            onColumnClick = navController::navigateToFadingEdgeColumn,
            onLazyColumnClick = navController::navigateToFadingEdgeLazyColumn,
            onAndroidViewClick = navController::navigateToFadingEdgeAndroidView
        )
    }
}

fun NavGraphBuilder.fadingEdgeColumnRoute() {
    composable(route = FadingEdgeColumnRoute) {
        FadingEdgeColumnScreen()
    }
}

fun NavController.navigateToFadingEdgeColumn() =
    navigate(FadingEdgeColumnRoute)

fun NavGraphBuilder.fadingEdgeLazyColumnRoute() {
    composable(route = FadingEdgeLazyColumnRoute) {
        FadingEdgeLazyColumnScreen()
    }
}

fun NavController.navigateToFadingEdgeLazyColumn() =
    navigate(FadingEdgeLazyColumnRoute)

fun NavGraphBuilder.fadingEdgeAndroidViewRoute() {
    composable(route = FadingEdgeAndroidViewRoute) {
        FadingEdgeAndroidViewScreen()
    }
}

fun NavController.navigateToFadingEdgeAndroidView() =
    navigate(FadingEdgeAndroidViewRoute)
