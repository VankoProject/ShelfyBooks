package com.kliachenko.presentation.auth

import com.kliachenko.presentation.core.BaseViewModel
import com.kliachenko.presentation.core.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.Initial)
    val authUiState: StateFlow<AuthUiState> = _authUiState

    fun signInWithGoogle(navigate: () -> Unit) {
        _authUiState.value = AuthUiState.Progress
        navigate.invoke()
    }
}