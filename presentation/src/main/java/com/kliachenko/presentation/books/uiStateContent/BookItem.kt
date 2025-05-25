package com.kliachenko.presentation.books.uiStateContent

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.SellersButtonUiState
import com.kliachenko.presentation.books.model.BookUi
import com.kliachenko.presentation.books.model.SellerUi

@Composable
fun BookItem(
    book: BookUi,
    buttonUiState: SellersButtonUiState,
    onSellersClick: (List<SellerUi>) -> Unit
) {
    book.Show(
        buttonUiState = buttonUiState,
        onSellersClick = onSellersClick,
        imageContent = { modifier ->
            AsyncImage(
                model = book.getImageUrl(),
                contentDescription = stringResource(R.string.book_image),
                modifier = modifier,
                contentScale = ContentScale.Fit,
                placeholder = painterResource(R.drawable.book_image_placeholder),
                error = painterResource(R.drawable.book_image_placeholder)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBookItemInitial() {
    BookItem(
        book = BookUi.Base(
            title = "Preview Title",
            description = "A young man becomes the caretaker.",
            author = "Author Name",
            publisher = "Publisher Name",
            imageUrl = "",
            rank = 1,
            sellers = listOf(SellerUi.Base("Amazon", ""), SellerUi.Base("Apple", ""))
        ),
        buttonUiState = SellersButtonUiState.BuyAction
    ) {}

}

@Preview(showBackground = true)
@Composable
fun PreviewBookItemProgress() {
    BookItem(
        book = BookUi.Base(
            title = "Preview Title",
            description = "A young man becomes the caretaker.",
            author = "Author Name",
            publisher = "Publisher Name",
            imageUrl = "",
            rank = 1,
            sellers = listOf(SellerUi.Base("Amazon", ""), SellerUi.Base("Apple", ""))
        ),
        buttonUiState = SellersButtonUiState.Progress
    ) {}

}