package com.kliachenko.presentation.books.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.SellerButton
import com.kliachenko.presentation.books.SellersButtonUiState

@Composable
fun BookItemConstraint(
    title: String,
    description: String,
    author: String,
    publisher: String,
    sellers: List<SellerUi>,
    buttonUiState: SellersButtonUiState,
    onSellersClick: (List<SellerUi>) -> Unit,
    imageContent: @Composable (Modifier) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(114.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
        ) {
            val (imageRef, titleRef, descRef, authorRef, publisherRef, buttonRef) = createRefs()

            imageContent(
                Modifier
                    .size(60.dp).padding(4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .constrainAs(imageRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                modifier = Modifier.constrainAs(titleRef) {
                    start.linkTo(imageRef.end, margin = 4.dp)
                    top.linkTo(parent.top, margin = 4.dp)
                    end.linkTo(parent.end, margin = 4.dp)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = description,
                fontSize = 10.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Light,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                modifier = Modifier
                    .constrainAs(descRef) {
                        top.linkTo(titleRef.bottom, margin = 4.dp)
                        start.linkTo(titleRef.start)
                        end.linkTo(parent.end, margin = 8.dp)
                        width = Dimension.fillToConstraints
                    }
            )

            BookAuthor(
                author = author,
                modifier = Modifier.constrainAs(authorRef) {
                    start.linkTo(titleRef.start)
                    end.linkTo(
                        buttonRef.start,
                        margin = 4.dp
                    )
                    bottom.linkTo(publisherRef.top, margin = 4.dp)
                    width = Dimension.fillToConstraints
                }
            )

            BookPublisher(
                publisher = publisher,
                modifier = Modifier.constrainAs(publisherRef) {
                    start.linkTo(titleRef.start)
                    bottom.linkTo(parent.bottom, margin = 4.dp)
                }
            )

            SellerButton(
                sellers = sellers,
                state = buttonUiState,
                onButtonClick = onSellersClick,
                modifier = Modifier
                    .padding(4.dp)
                    .height(20.dp)
                    .constrainAs(buttonRef) {
                        end.linkTo(parent.end, margin = 4.dp)
                        top.linkTo(authorRef.top)
                        bottom.linkTo(publisherRef.bottom)
                    }
            )
        }
    }

}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun PreviewBooItemConstraint() {
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
                modifier = Modifier,
                buttonUiState = SellersButtonUiState.BuyAction,
                onSellersClick = {}) { modifier ->
                Image(
                    modifier = modifier,
                    painter = painterResource(R.drawable.book_image_placeholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}