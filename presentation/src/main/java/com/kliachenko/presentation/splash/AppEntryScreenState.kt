package com.kliachenko.presentation.splash

sealed interface AuthState {

    data object Initial : AuthState

    data object Authorized : AuthState

    data object Unauthorized : AuthState

}