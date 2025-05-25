package com.kliachenko.presentation.books.uiStateContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.BookUiState
import com.kliachenko.presentation.books.SellersButtonUiState
import com.kliachenko.presentation.uiComponents.dialog.DialogUiState

@Composable
fun BooksProgressStateContent(categoryName: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.loading_books_message, categoryName),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(54.dp))
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun PreviewProgressState() {
    BookUiState.Progress(categoryName = "Hardcover Fiction").Show(
        buttonUiState = SellersButtonUiState.BuyAction,
        dialogUiState = DialogUiState.None,
        categoryName = "Hardcover Fiction Category",
        navigate = {},
        onRetry = {},
        onSellersClick = {},
        onDialogDismiss = {}
    )

}