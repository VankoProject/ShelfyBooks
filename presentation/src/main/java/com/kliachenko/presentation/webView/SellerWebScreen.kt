package com.kliachenko.presentation.webView

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kliachenko.presentation.navigation.NavigationState

@Composable
fun SellerWebScreen(
    navigation: NavigationState,
    pageTitle: String,
    sellerLink: String,
) {

    val viewModel: SellerWebViewModel = hiltViewModel()

    val sellerWebUiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(sellerLink) {
        viewModel.loadUrl(sellerLink)
    }

    SellerWebStateContent(
        sellerWebUiState = sellerWebUiState,
        pageTitle = pageTitle,
        sellerLink = sellerLink,
        onBack = { navigation.popBackStack() },
        onRetry = { viewModel.retry() }
    )

}