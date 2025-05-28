package com.kliachenko.presentation.categories

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kliachenko.presentation.navigation.Navigation

@Composable
fun CategoriesScreen(
    navigation: Navigation.ToBooksScreen
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
            navigation.navigateToBooksWithArgs(categoryId, categoryName)
        })

}