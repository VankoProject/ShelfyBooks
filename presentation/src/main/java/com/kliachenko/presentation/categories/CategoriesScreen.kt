package com.kliachenko.presentation.categories

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.kliachenko.presentation.navigation.NavigationState

@Composable
fun CategoriesScreen(
    navigation: NavigationState
) {

    val viewModel: CategoriesViewModel = hiltViewModel()

}