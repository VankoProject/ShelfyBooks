package com.kliachenko.presentation.books

import com.kliachenko.domain.usecase.BooksByCategory
import com.kliachenko.presentation.books.model.SellerUi
import com.kliachenko.presentation.books.model.mapper.BookResultMapper
import com.kliachenko.presentation.core.BaseViewModel
import com.kliachenko.presentation.core.RunAsync
import com.kliachenko.presentation.uiComponents.dialog.DialogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksByCategory: BooksByCategory,
    private val booksMapper: BookResultMapper,
    private val bookStoreLinkHandler: BookStoreLinkHandler,
    runAsync: RunAsync,
) : BaseViewModel(runAsync) {

    private var _uiState = MutableStateFlow<BookUiState>(BookUiState.Empty)
    val uiState: StateFlow<BookUiState> = _uiState

    private var _dialogState = MutableStateFlow<DialogUiState>(DialogUiState.None)
    val dialogState: StateFlow<DialogUiState> = _dialogState

    fun load(categoryId: String, categoryName: String) {
        _uiState.value = BookUiState.Progress(categoryName = categoryName)
        runAsync(
            booksByCategory(categoryId)
        ) { result ->
            _uiState.value = result.map(booksMapper)
        }
    }

    fun sellers(sellers: List<SellerUi>) {
        _dialogState.value = DialogUiState.Sellers(
            sellers = sellers,
            onSellerClick = { seller -> seller.buy(bookStoreLinkHandler) }
        )
    }

    fun dismissDialog() {
        _dialogState.value = DialogUiState.None
    }

}