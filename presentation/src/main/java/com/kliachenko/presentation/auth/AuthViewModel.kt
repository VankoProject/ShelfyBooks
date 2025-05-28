package com.kliachenko.presentation.auth

import android.util.Log
import com.kliachenko.domain.core.HandleError
import com.kliachenko.domain.repository.AuthResult
import com.kliachenko.domain.usecase.SignInWithGoogle
import com.kliachenko.presentation.core.BaseViewModel
import com.kliachenko.presentation.core.RunAsync
import com.kliachenko.presentation.uiComponents.dialog.DialogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInWithGoogle: SignInWithGoogle,
    private val resultMapper: AuthResultMapper,
    private val handleError: HandleError<String>,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val authUiState: StateFlow<AuthUiState> = _authUiState

    private val _dialogUiState = MutableStateFlow<DialogUiState>(DialogUiState.None)
    val dialogUiState: StateFlow<DialogUiState> = _dialogUiState

    fun signInWithGoogle(idToken: String, navigate: () -> Unit) {
        runAsync({
            _authUiState.value = AuthUiState.Progress
            signInWithGoogle(idToken)
        }) { authResult ->
            val message = authResult.map(resultMapper)
            when (authResult) {
                is AuthResult.Success -> { navigate() }

                is AuthResult.Error -> {
                    _authUiState.value = AuthUiState.Initial
                    _dialogUiState.value = DialogUiState.Error(message)
                }
            }
        }
    }

    fun showError(e: Exception) {
        _dialogUiState.value = DialogUiState.Error(
            handleError.handle(e)
        )
    }

    fun dismissDialog() {
        _dialogUiState.value = DialogUiState.None
    }

}