package com.kliachenko.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kliachenko.presentation.auth.AuthScreen
import com.kliachenko.presentation.auth.ProvideGoogleIdToken
import com.kliachenko.presentation.books.BooksScreen
import com.kliachenko.presentation.categories.CategoriesScreen
import com.kliachenko.presentation.navigation.AppGraph
import com.kliachenko.presentation.navigation.AppNavGraph
import com.kliachenko.presentation.navigation.rememberNavigationState
import com.kliachenko.presentation.splash.SplashScreen
import com.kliachenko.presentation.ui.theme.ShelfyBooksTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var provideIdToken: ProvideGoogleIdToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShelfyBooksTheme {
                NavApp(
                    modifier = Modifier,
                    googleIdToken = provideIdToken
                )
            }
        }
    }

}

@Composable
fun NavApp(
    modifier: Modifier,
    googleIdToken: ProvideGoogleIdToken,
) {

    val navController: NavHostController = rememberNavController()
    val navigationState = rememberNavigationState(navController)
    val startDestination = AppGraph.SplashGraph.Splash.route()

    AppNavGraph(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        splashScreenContent = { SplashScreen(navigationState) },
        authScreenContent = { AuthScreen(googleIdToken, navigationState) },
        categoriesScreenContent = { CategoriesScreen(navigationState) },
        booksScreenContent = { categoryId, categoryName ->
            BooksScreen(
                navigationState,
                categoryId,
                categoryName
            )
        })

}
