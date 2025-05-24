package com.kliachenko.presentation.books.uiStateContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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

@Composable
fun BooksErrorStateContent(
    errorMessage: String, onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onRetry.invoke() }) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun PreviewErrorState() {
    BookUiState.Error("No internet connection").Show(
        categoryName = "Hardcover Fiction Category",
        navigate = {},
        onRetry = {},
        onSellersClick = {}
    )
}