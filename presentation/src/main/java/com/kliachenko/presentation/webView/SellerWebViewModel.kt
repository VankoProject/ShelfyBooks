package com.kliachenko.presentation.webView

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kliachenko.domain.core.HandleError
import com.kliachenko.presentation.core.HandleWebViewException
import com.kliachenko.presentation.navigation.NavGraphKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SellerWebViewModel @Inject constructor(
    private val handleError: HandleError<String>,
    private val handleWebViewException: HandleWebViewException,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<SellerWebViewUiState>(SellerWebViewUiState.Progress)
    val uiState: StateFlow<SellerWebViewUiState> = _uiState.asStateFlow()

    private val sellerLink = savedStateHandle.get<String>(NavGraphKeys.SELLER_LINK) ?: ""

    fun loadUrl(sellerLink: String) {
        _uiState.value = SellerWebViewUiState.Progress
        try {
            _uiState.value =
                SellerWebViewUiState.SuccessSellerWebContent(sellerLink) { errorCode, description ->
                    val message = handleWebViewException.handle(errorCode, description)
                    _uiState.value = SellerWebViewUiState.Error(message)
                }
        } catch (e: Exception) {
            val errorMessage = handleError.handle(e)
            _uiState.value = SellerWebViewUiState.Error(errorMessage)
        }
    }

    fun retry() {
        _uiState.value = SellerWebViewUiState.Progress
        loadUrl(sellerLink = sellerLink)
    }

}