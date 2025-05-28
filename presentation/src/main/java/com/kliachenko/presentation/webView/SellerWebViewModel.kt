package com.kliachenko.presentation.webView

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kliachenko.presentation.core.HandleWebViewException
import com.kliachenko.presentation.navigation.NavGraphKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SellerWebViewModel @Inject constructor(
    private val handleWebViewException: HandleWebViewException,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<SellerWebViewUiState>(SellerWebViewUiState.Progress)
    val uiState: StateFlow<SellerWebViewUiState> = _uiState.asStateFlow()

    private val sellerLink = savedStateHandle.get<String>(NavGraphKeys.SELLER_LINK) ?: ""
    private val pageTitle = savedStateHandle.get<String>(NavGraphKeys.PAGE_TITLE) ?: ""

    fun loadUrl(sellerLink: String) {
        _uiState.value = SellerWebViewUiState.Progress

        _uiState.value = SellerWebViewUiState.SuccessSellerWebContent(
            pageTitle = pageTitle,
            link = sellerLink,
            onError = { code, description ->
                val message = handleWebViewException.handle(code, description)
                _uiState.value = SellerWebViewUiState.Error(message)
            }
        )
    }

    fun retry() {
        _uiState.value = SellerWebViewUiState.Progress
        loadUrl(sellerLink = sellerLink)
    }

}