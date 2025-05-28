package com.kliachenko.presentation.books

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kliachenko.presentation.R
import com.kliachenko.presentation.navigation.AppGraph
import com.kliachenko.presentation.navigation.NavigationState

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
    val buttonState by viewModel.buttonUiState.collectAsState()

    LaunchedEffect(categoryId) {
        viewModel.load(categoryId, categoryName)
    }

    val navigateBackToCategories = {
        navigation.navigateAndReplace(AppGraph.MainGraph.CategoriesGraph.Categories)
    }

    uiState.Show(
        buttonUiState = buttonState,
        dialogUiState = dialogState,
        categoryName = displayCategoryName,
        navigate = navigateBackToCategories,
        onRetry = { viewModel.load(categoryId, categoryName) },
        onSellersClick = { sellers ->
            viewModel.sellers(sellers = sellers) { name, url ->
                navigation.navigate(
                    AppGraph.MainGraph.WebViewGraph.Seller(
                        pageTitle = name,
                        sellerLink = url
                    )
                )
            }
        },
        onDialogDismiss = { viewModel.dismissDialog() })

}