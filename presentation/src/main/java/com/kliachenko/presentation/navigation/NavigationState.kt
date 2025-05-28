package com.kliachenko.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import javax.inject.Inject

class NavigationState(
    private val navHostController: NavHostController
) {

    fun navigate(graph: AppGraph) {
        navHostController.navigate(graph.route())
    }

    fun navigateAndReplace(graph: AppGraph) {
        navHostController.navigate(graph.route()) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                inclusive = true
                saveState = false
            }
            launchSingleTop = true
            restoreState = false
        }
    }

    fun popBackStack() {
        navHostController.popBackStack()
    }

}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController
): NavigationState = remember {
    NavigationState(navHostController)
}