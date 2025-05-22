package com.kliachenko.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    splashScreenContent: @Composable () -> Unit,
    authScreenContent: @Composable () -> Unit,
    categoriesScreenContent: @Composable () -> Unit,
    booksScreenContent: @Composable (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = AppGraph.SplashGraph.Splash.route()) {
            splashScreenContent()
        }

        composable(route = AppGraph.AuthGraph.Auth.route()) {
            authScreenContent()
        }

        navigation(
            startDestination = AppGraph.MainGraph.CategoriesGraph.Categories.route(),
            route = AppGraph.MainGraph.route()
        ) {
            composable(route = AppGraph.MainGraph.CategoriesGraph.Categories.route()) {
                categoriesScreenContent()
            }

            composable(
                route = AppGraph.MainGraph.BooksGraph.Books(categoryId = "").pattern(),
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
                booksScreenContent(categoryId)
            }
        }
    }

}