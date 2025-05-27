package com.kliachenko.presentation.books.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.SellersButtonUiState

interface BookUi {

    @Composable
    fun Show(
        modifier: Modifier,
        buttonUiState: SellersButtonUiState,
        onSellersClick: (List<SellerUi>) -> Unit,
        imageContent: @Composable (Modifier) -> Unit
    )

    fun getImageUrl(): String

    fun bookTitle(): String

    data class Base(
        private val title: String,
        private val description: String,
        private val author: String,
        private val publisher: String,
        private val imageUrl: String,
        private val rank: Int,
        private val sellers: List<SellerUi>
    ) : BookUi {
        @Composable
        override fun Show(
            modifier: Modifier,
            buttonUiState: SellersButtonUiState,
            onSellersClick: (List<SellerUi>) -> Unit,
            imageContent: @Composable (Modifier) -> Unit
        ) {

            val safeDescription = description.takeIf { it.isNotBlank() }
                ?: stringResource(id = R.string.no_description_available)

            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(114.dp),
                    contentAlignment = Alignment.Center
                ) {
                    BookRateUiState.Base(rank).Show()
                }

                Spacer(modifier = Modifier.width(4.dp))

                BookItemConstraint(
                    title = title,
                    description = safeDescription,
                    author = author,
                    publisher = publisher,
                    sellers = sellers,
                    buttonUiState = buttonUiState,
                    onSellersClick = onSellersClick,
                    imageContent = imageContent
                )

            }
        }


        override fun getImageUrl() = imageUrl

        override fun bookTitle() = title
    }
}

@Composable
fun BookAuthor(author: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(bottom = 0.dp)) {
        Text(
            text = stringResource(R.string.book_author),
            fontSize = 10.sp,
            lineHeight = 10.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = author,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 10.sp,
            lineHeight = 10.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun BookPublisher(publisher: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(bottom = 0.dp)) {
        Text(
            text = stringResource(R.string.book_publisher),
            fontSize = 10.sp,
            lineHeight = 10.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = publisher,
            fontSize = 10.sp,
            lineHeight = 10.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun BookUiPreview() {
    val fakeList = listOf(
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
    )

    Column {
        fakeList.forEach {
            it.Show(
                modifier = Modifier.fillMaxWidth(),
                buttonUiState = SellersButtonUiState.BuyAction,
                onSellersClick = {}) { modifier ->
                Image(
                    painter = painterResource(R.drawable.book_image_placeholder),
                    contentDescription = null,
                    modifier = modifier,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}