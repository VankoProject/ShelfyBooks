package com.kliachenko.presentation.categories.uiStateContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kliachenko.presentation.R
import com.kliachenko.presentation.categories.CategoriesUiState
import com.kliachenko.presentation.categories.models.CategoryUi
import com.kliachenko.presentation.ui.theme.ShelfyBooksTheme

@Composable
fun CategorySuccessStateContent(
    publishedDate: String,
    categories: List<CategoryUi>,
    onItemClick: (String, String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.published_date, publishedDate),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            lineHeight = 12.sp,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.choose_a_category),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            lineHeight = 18.sp,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { categoryItem ->
                categoryItem.Show(
                    modifier = Modifier
                        .fillMaxWidth()
                ) { id, categoryName -> onItemClick(id, categoryName) }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSuccessStateContent() {
    val fakeCategories: List<CategoryUi> = listOf(
        CategoryUi.Base("1", "Fiction", 12, "weekly"),
        CategoryUi.Base("2", "Science", 8, "monthly"),
        CategoryUi.Base("3", "History", 5, "weekly")
    )

    ShelfyBooksTheme {
        Surface {
            CategoriesUiState.Success(
                publishedDate = "2025-05-23",
                categories = fakeCategories,
            ).Show(
                onRetry = {},
                onItemClick = { _, _ -> }
            )
        }
    }

}