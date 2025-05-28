package com.kliachenko.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.kliachenko.presentation.navigation.NavGraphKeys.CATEGORY_ID
import com.kliachenko.presentation.navigation.NavGraphKeys.CATEGORY_NAME
import com.kliachenko.presentation.navigation.NavGraphKeys.PAGE_TITLE
import com.kliachenko.presentation.navigation.NavGraphKeys.SELLER_LINK

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String,
    splashScreenContent: @Composable () -> Unit,
    authScreenContent: @Composable () -> Unit,
    categoriesScreenContent: @Composable () -> Unit,
    booksScreenContent: @Composable (String, String) -> Unit,
    sellerWebViewContent: @Composable (String, String) -> Unit,
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
                route = AppGraph.MainGraph.BooksGraph.Books(categoryId = "", categoryName = "")
                    .pattern(),
                arguments = listOf(
                    navArgument(CATEGORY_ID) { type = NavType.StringType },
                    navArgument(CATEGORY_NAME) { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getString(CATEGORY_ID) ?: ""
                val categoryName = backStackEntry.arguments?.getString(CATEGORY_NAME) ?: ""
                booksScreenContent(categoryId, categoryName)
            }

            composable(
                route = AppGraph.MainGraph.WebViewGraph.Seller(pageTitle = "", sellerLink = "")
                    .pattern(),
                arguments = listOf(
                    navArgument(PAGE_TITLE) { type = NavType.StringType },
                    navArgument(SELLER_LINK) { type = NavType.StringType }
                )
            ) { navBackStackEntry ->
                val pageTitle = navBackStackEntry.arguments?.getString(PAGE_TITLE) ?: ""
                val sellerLink = navBackStackEntry.arguments?.getString(SELLER_LINK) ?: ""
                sellerWebViewContent(pageTitle, sellerLink)
            }
        }
    }

}

internal object NavGraphKeys {
    const val CATEGORY_ID = "categoryId"
    const val CATEGORY_NAME = "categoryName"
    const val PAGE_TITLE = "pageTitle"
    const val SELLER_LINK = "sellerLink"
}