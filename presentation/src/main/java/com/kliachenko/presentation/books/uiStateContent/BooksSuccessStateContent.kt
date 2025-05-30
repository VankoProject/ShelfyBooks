package com.kliachenko.presentation.books.uiStateContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.SellersButtonUiState
import com.kliachenko.presentation.books.model.BookUi
import com.kliachenko.presentation.books.model.SellerUi
import com.kliachenko.presentation.uiComponents.dialog.DialogUiState
import com.kliachenko.presentation.uiComponents.dialog.HandleDialog

@Composable
fun BooksSuccessStateContent(
    buttonUiState: SellersButtonUiState,
    dialogUiState: DialogUiState,
    books: List<BookUi>,
    onSellersClick: (List<SellerUi>) -> Unit,
    onDialogDismiss: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.rate),
                color = MaterialTheme.colorScheme.onBackground)
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.books)
            )
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(books, key = { it.bookTitle() }) { bookUi ->
                BookItem(
                    modifier = Modifier.fillMaxWidth(),
                    book = bookUi,
                    buttonUiState = buttonUiState,
                    onSellersClick = onSellersClick
                )
            }
        }
        HandleDialog(dialogUiState = dialogUiState, onDismiss = onDialogDismiss)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBooksSuccessStateContent() {
    BooksSuccessStateContent(
        dialogUiState = DialogUiState.None,
        books = listOf(
            BookUi.Base(
                title = "Preview Title",
                description = "A young man becomes the caretaker.",
                author = "Author Name",
                publisher = "Publisher Name",
                imageUrl = "",
                rank = 1,
                sellers = listOf(SellerUi.Base("Amazon", ""), SellerUi.Base("Apple", ""))
            ),
            BookUi.Base(
                title = "Preview Title",
                description = "The second book in the Saga of the Unfated series. As war seems imminent, Freya fights a battle within herself.",
                author = "Author Name",
                publisher = "Publisher Name",
                imageUrl = "",
                rank = 2,
                sellers = listOf(SellerUi.Base("Amazon", ""), SellerUi.Base("Apple", ""))
            ),
            BookUi.Base(
                title = "Preview Title",
                description = "A young woman looks into the story behind a painting that was made 25 years ago and a small group of teens depicted in it; translated by Neil Smith.",
                author = "Author Name",
                publisher = "Publisher Name",
                imageUrl = "",
                rank = 3,
                sellers = listOf(SellerUi.Base("Amazon", ""), SellerUi.Base("Apple", ""))
            )
        ),
        onSellersClick = {},
        onDialogDismiss = {},
        buttonUiState = SellersButtonUiState.BuyAction
    )

}