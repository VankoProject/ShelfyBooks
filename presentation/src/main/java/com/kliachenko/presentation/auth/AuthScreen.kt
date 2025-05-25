package com.kliachenko.presentation.auth

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.exceptions.GetCredentialException
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kliachenko.presentation.navigation.AppGraph
import com.kliachenko.presentation.navigation.NavigationState
import kotlinx.coroutines.launch

@SuppressLint("ContextCastToActivity")
@Composable
fun AuthScreen(
    googleIdToken: ProvideGoogleIdToken,
    navigation: NavigationState
) {

    val viewModel: AuthViewModel = hiltViewModel()
    val authUiState by viewModel.authUiState.collectAsStateWithLifecycle()
    val dialogUiState by viewModel.dialogUiState.collectAsState()
    val activity = LocalContext.current as Activity
    val coroutineScope = rememberCoroutineScope()

    authUiState.Show(
        dialogUiState = dialogUiState,
        onSignIn = {
            coroutineScope.launch {
                val token = try {
                    googleIdToken.token(activity)
                } catch (e: GetCredentialException) {
                    viewModel.showError(e)
                    null
                } catch (e: Exception) {
                    viewModel.showError(e)
                    null
                }

                if (token != null) {
                    viewModel.signInWithGoogle(token) {
                        navigation.navigateAndReplace(AppGraph.MainGraph.CategoriesGraph.Categories)
                    }
                }
            }
        },
        onDismiss = { viewModel.dismissDialog() })

}