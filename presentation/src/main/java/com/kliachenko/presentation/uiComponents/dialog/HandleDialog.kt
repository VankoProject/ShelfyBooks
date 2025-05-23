package com.kliachenko.presentation.uiComponents.dialog

import androidx.compose.runtime.Composable

@Composable
fun HandeDialog(
    dialogState: DialogState,
    onDismiss: () -> Unit,
    onRetryButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit
) {

    dialogState.Show(
        onDismiss = { onDismiss.invoke() },
        onRetryButtonClick = { onRetryButtonClick.invoke() },
        onCancelButtonClick = { onCancelButtonClick.invoke() })

}

interface DialogState {

    @Composable
    fun Show(
        onDismiss: () -> Unit,
        onRetryButtonClick: () -> Unit,
        onCancelButtonClick: () -> Unit
    ) = Unit

    data object None : DialogState

    data class Error(
        private val errorMessage: String
    ) : DialogState {

        @Composable
        override fun Show(
            onDismiss: () -> Unit,
            onRetryButtonClick: () -> Unit,
            onCancelButtonClick: () -> Unit
        ) {

        }
    }

    data class Success(private val successMessage: String) : DialogState {

        @Composable
        override fun Show(
            onDismiss: () -> Unit,
            onRetryButtonClick: () -> Unit,
            onCancelButtonClick: () -> Unit
        ) {
            SuccessDialog(message = successMessage) {
                onDismiss.invoke()
            }
        }

    }

}