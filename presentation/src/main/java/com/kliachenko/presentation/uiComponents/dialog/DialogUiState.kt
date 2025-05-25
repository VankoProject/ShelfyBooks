package com.kliachenko.presentation.uiComponents.dialog

import androidx.compose.runtime.Composable
import com.kliachenko.presentation.books.model.SellerUi

@Composable
fun HandleDialog(
    dialogUiState: DialogUiState,
    onDismiss: () -> Unit,
    onRetryButtonClick: () -> Unit = {},
    onCancelButtonClick: () -> Unit = {},
) {
    dialogUiState.Show(
        onDismiss = onDismiss,
        onRetryButtonClick = onRetryButtonClick,
        onCancelButtonClick = onCancelButtonClick
    )
}

interface DialogUiState {

    @Composable
    fun Show(
        onDismiss: () -> Unit,
        onRetryButtonClick: () -> Unit,
        onCancelButtonClick: () -> Unit
    ) = Unit

    data object None : DialogUiState

    data class Error(
        private val errorMessage: String
    ) : DialogUiState {

        @Composable
        override fun Show(
            onDismiss: () -> Unit,
            onRetryButtonClick: () -> Unit,
            onCancelButtonClick: () -> Unit
        ) {
            ErrorDialog(
                errorMessage = errorMessage,
                onDismiss = onDismiss,
                onRetry = onRetryButtonClick,
                onCancel = onCancelButtonClick
            )
        }
    }

    data class Success(private val successMessage: String) : DialogUiState {

        @Composable
        override fun Show(
            onDismiss: () -> Unit,
            onRetryButtonClick: () -> Unit,
            onCancelButtonClick: () -> Unit
        ) {
            SuccessDialog(message = successMessage, onDismiss)
        }
    }

    data class Sellers(
        private val sellers: List<SellerUi>,
        private val onSellerClick: (SellerUi) -> Unit
    ) : DialogUiState {

        @Composable
        override fun Show(
            onDismiss: () -> Unit,
            onRetryButtonClick: () -> Unit,
            onCancelButtonClick: () -> Unit
        ) {
            SellersDialog(
                sellers = sellers,
                onDismiss = onDismiss,
                onSellerClick = onSellerClick
            )
        }
    }

}