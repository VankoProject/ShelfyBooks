package com.kliachenko.presentation.books.uiStateContent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.SellersButtonUiState
import com.kliachenko.presentation.books.model.BookUi
import com.kliachenko.presentation.books.model.SellerUi

@Composable
fun BookItem(
    modifier: Modifier,
    book: BookUi,
    buttonUiState: SellersButtonUiState,
    onSellersClick: (List<SellerUi>) -> Unit
) {

    val context = LocalContext.current

    val imageRequest = remember(key1 = book.getImageUrl()) {
        ImageRequest.Builder(context)
            .data(book.getImageUrl())
            .addHeader("User-Agent", "ShelfyBooks-Client")
            .crossfade(true)
            .build()
    }

    book.Show(
        modifier = modifier,
        buttonUiState = buttonUiState,
        onSellersClick = onSellersClick,
        imageContent = {imageModifier->
            AsyncImage(
                modifier = imageModifier,
                model = imageRequest,
                contentDescription = stringResource(R.string.book_image),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(R.drawable.book_image_placeholder),
                error = painterResource(R.drawable.book_image_placeholder)
            )
        }
    )
}

@Preview(showBackground = true, apiLevel = 34)
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
        buttonUiState = SellersButtonUiState.BuyAction,
        modifier = Modifier.fillMaxWidth()
    ) {}

}