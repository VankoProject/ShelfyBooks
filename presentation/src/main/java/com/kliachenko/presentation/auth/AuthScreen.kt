package com.kliachenko.presentation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kliachenko.presentation.navigation.AppGraph
import com.kliachenko.presentation.navigation.NavigationState

@Composable
fun AuthScreen(
    navigation: NavigationState
) {

    val viewModel: AuthViewModel = hiltViewModel()

    val authUiState by viewModel.authUiState.collectAsStateWithLifecycle()

    authUiState.Show {
        viewModel.signInWithGoogle {
            navigation.navigateAndReplace(AppGraph.MainGraph.CategoriesGraph.Categories)
        }
    }
}