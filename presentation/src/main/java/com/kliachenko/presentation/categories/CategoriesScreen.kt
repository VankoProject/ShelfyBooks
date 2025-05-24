package com.kliachenko.presentation.categories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.kliachenko.presentation.navigation.AppGraph
import com.kliachenko.presentation.navigation.NavigationState

@Composable
fun CategoriesScreen(
    navigation: NavigationState
) {

    val viewModel: CategoriesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    uiState.Show(
        onRetry = { viewModel.load() },
        onItemClick = { categoryId ->
            navigation.navigate(
                graph = AppGraph.MainGraph.BooksGraph.Books(
                    categoryId
                )
            )
        })

}