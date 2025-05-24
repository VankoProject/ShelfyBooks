package com.kliachenko.presentation.books.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kliachenko.presentation.R

interface BookUi {

    @Composable
    fun Show(
        onSellersClick: (List<SellerUi>) -> Unit,
        imageContent: @Composable (Modifier) -> Unit
    )

    fun getImageUrl(): String

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
            onSellersClick: (List<SellerUi>) -> Unit,
            imageContent: @Composable (Modifier) -> Unit
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                BookRateUiState.Base(rank).Show()

                Spacer(modifier = Modifier.width(4.dp))

                Card(
                    modifier = Modifier.height(114.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {

                        imageContent(
                            Modifier
                                .width(34.dp)
                                .height(52.dp)
                                .clip(RoundedCornerShape(4.dp))
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(
                            modifier = Modifier.wrapContentHeight(),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                modifier = Modifier.padding(bottom = 8.dp),
                                text = title,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                modifier = Modifier.weight(1f),
                                text = description,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 3,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Light
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Column {
                                    BookAuthor(author)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    BookPublisher(publisher)
                                }
                                Button(
                                    modifier = Modifier.height(20.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    onClick = { onSellersClick(sellers) }) {
                                    Text(
                                        text = stringResource(R.string.book_buy_button),
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }

        override fun getImageUrl() = imageUrl
    }
}

@Composable
fun BookAuthor(author: String) {
    Row {
        Text(
            text = stringResource(R.string.book_author),
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = author,
            fontSize = 10.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun BookPublisher(publisher: String) {
    Row {
        Text(
            text = stringResource(R.string.book_publisher),
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = publisher,
            fontSize = 10.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview(showBackground = true)
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
            it.Show(onSellersClick = {}) { modifier ->
                Image(
                    painter = painterResource(R.drawable.book_image_placeholder),
                    contentDescription = null,
                    modifier = modifier
                        .width(60.dp)
                        .height(80.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}