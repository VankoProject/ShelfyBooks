package com.kliachenko.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kliachenko.presentation.auth.AuthScreen
import com.kliachenko.presentation.auth.ProvideGoogleIdToken
import com.kliachenko.presentation.books.BooksScreen
import com.kliachenko.presentation.categories.CategoriesScreen
import com.kliachenko.presentation.navigation.AppGraph
import com.kliachenko.presentation.navigation.AppNavGraph
import com.kliachenko.presentation.navigation.Navigation
import com.kliachenko.presentation.navigation.rememberNavigationState
import com.kliachenko.presentation.splash.SplashScreen
import com.kliachenko.presentation.ui.theme.ShelfyBooksTheme
import com.kliachenko.presentation.webView.SellerWebScreen
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
                    googleIdToken = provideIdToken,
                )
            }
        }
    }

}

@Composable
fun NavApp(
    googleIdToken: ProvideGoogleIdToken,
) {

    val navController: NavHostController = rememberNavController()
    val navigationState = rememberNavigationState(navController)
    val startDestination = AppGraph.SplashGraph.Splash.route()

    val navigation: Navigation.Mutable = remember {
        Navigation.Base(navigationState)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        color = MaterialTheme.colorScheme.background
    ) {
        AppNavGraph(
            navController = navController,
            startDestination = startDestination,
            splashScreenContent = { SplashScreen(navigation) },
            authScreenContent = { AuthScreen(googleIdToken, navigation) },
            categoriesScreenContent = { CategoriesScreen(navigation) },
            booksScreenContent = { categoryId, categoryName ->
                BooksScreen(
                    navigationState,
                    categoryId,
                    categoryName
                )
            },
            sellerWebViewContent = { pageTitle, sellerLink ->
                SellerWebScreen(
                    navigation, pageTitle, sellerLink
                )
            })
    }

}
