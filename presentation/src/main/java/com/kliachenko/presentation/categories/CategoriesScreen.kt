package com.kliachenko.presentation.categories

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kliachenko.presentation.navigation.AppGraph
import com.kliachenko.presentation.navigation.NavigationState

@Composable
fun CategoriesScreen(
    navigation: NavigationState
) {

    val context = LocalContext.current

    BackHandler {
        val activity = context as Activity
        activity.finish()
    }

    val viewModel: CategoriesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    uiState.Show(
        onRetry = { viewModel.load() },
        onItemClick = { categoryId, categoryName ->
            navigation.navigate(
                graph = AppGraph.MainGraph.BooksGraph.Books(
                    categoryId, categoryName
                )
            )
        })

}