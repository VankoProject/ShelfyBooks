package com.kliachenko.presentation.books

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kliachenko.presentation.R
import com.kliachenko.presentation.navigation.NavigationState
import com.kliachenko.presentation.uiComponents.dialog.DialogUiState

@Composable
fun BooksScreen(
    navigation: NavigationState,
    categoryId: String,
    categoryName: String
) {

    val displayCategoryName = categoryName.ifBlank {
        stringResource(R.string.bestseller_category)
    }

    val viewModel: BooksViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val dialogState by viewModel.dialogState.collectAsState()

    if (dialogState !is DialogUiState.None)
        dialogState.Show(
            onDismiss = { viewModel.dismissDialog() },
            onRetryButtonClick = { },
            onCancelButtonClick = { })

    LaunchedEffect(key1 = Unit) {
        viewModel.load(categoryId, categoryName)
    }


    uiState.Show(
        categoryName = displayCategoryName,
        navigate = { navigation.popBackStack() },
        onRetry = { viewModel.load(categoryId, categoryName) },
        onSellersClick = { sellers -> viewModel.sellers(sellers = sellers) })

}