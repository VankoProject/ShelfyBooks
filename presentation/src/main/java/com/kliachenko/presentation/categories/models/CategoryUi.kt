package com.kliachenko.presentation.categories.models

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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

interface CategoryUi {

    @Composable
    fun Show(modifier: Modifier, onItemClick: (String, String) -> Unit) = Unit

    data class Base(
        private val id: String,
        private val name: String,
        private val booksCount: Int,
        private val updatePeriod: String
    ) : CategoryUi {

        @Composable
        override fun Show(modifier: Modifier, onItemClick: (String, String) -> Unit) {
            CategoryUiContent(name = name, booksCount = booksCount, updatePeriod = updatePeriod) {
                onItemClick.invoke(id, name)
            }
        }
    }

}

@Composable
fun CategoryUiContent(
    modifier: Modifier = Modifier,
    name: String,
    booksCount: Int,
    updatePeriod: String,
    onItemClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onItemClick.invoke() }
    ) {
        Column {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 18.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                text = name
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.books_count, booksCount),
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .width(100.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.updated_period, updatePeriod),
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun CategoryItemPreview_NoFillMaxWidth() {
    CategoryUiContent(
        name = "Fiction",
        booksCount = 42,
        updatePeriod = "weekly",
        modifier = Modifier
            .padding(8.dp),
        onItemClick = {}
    )
}

@Preview(name = "С fillMaxWidth()", showBackground = true)
@Composable
fun CategoryItemPreview_FillMaxWidth() {
    CategoryUiContent(
        name = "Fiction",
        booksCount = 42,
        updatePeriod = "weekly",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onItemClick = {}
    )
}
