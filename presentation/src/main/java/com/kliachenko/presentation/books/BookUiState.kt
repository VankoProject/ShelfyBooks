package com.kliachenko.presentation.books

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.model.BookUi
import com.kliachenko.presentation.books.model.SellerUi
import com.kliachenko.presentation.books.uiStateContent.BooksErrorStateContent
import com.kliachenko.presentation.books.uiStateContent.BooksProgressStateContent
import com.kliachenko.presentation.books.uiStateContent.BooksSuccessStateContent

interface BookUiState {

    @Composable
    fun Show(
        categoryName: String,
        navigate: () -> Unit,
        onRetry: () -> Unit,
        onSellersClick: (List<SellerUi>) -> Unit
    ) = Unit

    @OptIn(ExperimentalMaterial3Api::class)
    abstract class Abstract(
        private val content: @Composable (
            onButtonClick: () -> Unit,
            onSellersClick: (List<SellerUi>) -> Unit,
        ) -> Unit
    ) : BookUiState {

        @Composable
        override fun Show(
            categoryName: String,
            navigate: () -> Unit,
            onRetry: () -> Unit,
            onSellersClick: (List<SellerUi>) -> Unit
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = categoryName) },
                        navigationIcon = {
                            Icon(
                                modifier = Modifier.clickable { navigate.invoke() },
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.go_back)
                            )
                        }
                    )
                }
            ) { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    content(onRetry, onSellersClick)
                }
            }
        }
    }

    data object Empty : BookUiState

    data class Error(
        private val errorMessage: String
    ) : Abstract(content = { onButtonClick, _ ->
        BooksErrorStateContent(errorMessage, onButtonClick)
    })

    data class Progress(
        private val categoryName: String
    ) : Abstract(content = { _, _ ->
        BooksProgressStateContent(categoryName)
    })

    data class Success(
        private val books: List<BookUi>,
    ) : Abstract(content = { _, onSellersClick ->
        BooksSuccessStateContent(books, onSellersClick = onSellersClick)
    })
}