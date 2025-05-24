package com.kliachenko.presentation.books.uiStateContent

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.model.BookUi
import com.kliachenko.presentation.books.model.SellerUi

@Composable
fun BookItem(book: BookUi, onSellersClick: (List<SellerUi>) -> Unit) {
    book.Show(
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