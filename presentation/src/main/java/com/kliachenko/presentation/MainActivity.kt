package com.kliachenko.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kliachenko.presentation.auth.AuthScreen
import com.kliachenko.presentation.books.BooksScreen
import com.kliachenko.presentation.categories.CategoriesScreen
import com.kliachenko.presentation.navigation.AppGraph
import com.kliachenko.presentation.navigation.AppNavGraph
import com.kliachenko.presentation.navigation.rememberNavigationState
import com.kliachenko.presentation.splash.SplashScreen
import com.kliachenko.presentation.ui.theme.ShelfyBooksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShelfyBooksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NavApp(modifier: Modifier) {

    val navController: NavHostController = rememberNavController()
    val navigationState = rememberNavigationState(navController)
    val startDestination = AppGraph.SplashGraph.Splash.route()

    AppNavGraph(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        splashScreenContent = { SplashScreen(navigationState) },
        authScreenContent = { AuthScreen(navigationState) },
        categoriesScreenContent = { CategoriesScreen(navigationState) },
        booksScreenContent = { categoryId, categoryName ->
            BooksScreen(
                navigationState,
                categoryId,
                categoryName
            )
        })

}
