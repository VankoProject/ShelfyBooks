package com.kliachenko.presentation.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kliachenko.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _appEntryScreenState = MutableStateFlow<AuthState>(AuthState.Initial)
    val appEntryScreenState: StateFlow<AuthState> = _appEntryScreenState

    init {
        viewModelScope.launch {
            delay(2000)
            val isLogged = repository.isLoggedIn()
            _appEntryScreenState.value = if (repository.isLoggedIn())
                AuthState.Authorized
            else
                AuthState.Unauthorized
        }
    }

}