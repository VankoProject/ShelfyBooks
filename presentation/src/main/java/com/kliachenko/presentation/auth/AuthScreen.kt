package com.kliachenko.presentation.auth

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kliachenko.presentation.navigation.Navigation
import kotlinx.coroutines.launch

@SuppressLint("ContextCastToActivity")
@Composable
fun AuthScreen(
    googleIdToken: ProvideGoogleIdToken,
    navigation: Navigation.ToCategoriesScreen
) {

    val viewModel: AuthViewModel = hiltViewModel()
    val authUiState by viewModel.authUiState.collectAsStateWithLifecycle()
    val dialogUiState by viewModel.dialogUiState.collectAsState()
    val activity = LocalContext.current as Activity
    val coroutineScope = rememberCoroutineScope()

    val navigateToCategories = { navigation.navigateToCategories() }

    val onSignIn: () -> Unit = {
        viewModel.dismissDialog()
        coroutineScope.launch {
            val result = googleIdToken.token(activity)
            result.map(object : TokenResult.Mapper<Unit> {

                override fun mapSuccess(data: String) {
                    viewModel.signInWithGoogle(data) {
                        navigateToCategories.invoke()
                    }
                }

                override fun mapError(exception: Exception) {
                    viewModel.showError(exception)
                }

                override fun mapCanceled() {}

            })
        }
    }

    authUiState.Show(
        dialogUiState = dialogUiState,
        onSignIn = onSignIn,
        onDismiss = { viewModel.dismissDialog() })

}