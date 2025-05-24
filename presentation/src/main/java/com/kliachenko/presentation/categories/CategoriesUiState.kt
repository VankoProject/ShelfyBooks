package com.kliachenko.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kliachenko.presentation.R
import com.kliachenko.presentation.categories.models.CategoryUi
import com.kliachenko.presentation.categories.uiStateContent.CategoryErrorStateContent
import com.kliachenko.presentation.categories.uiStateContent.CategoryProgressStateContent
import com.kliachenko.presentation.categories.uiStateContent.CategorySuccessStateContent

interface CategoriesUiState {

    @Composable
    fun Show(
        onClick: () -> Unit,
        onItemClick: (String) -> Unit
    ) = Unit

    abstract class Abstract(
        private val content: @Composable ColumnScope.(
            onClick: () -> Unit,
            onItemClick: (String) -> Unit
        ) -> Unit
    ) : CategoriesUiState {

        @Composable
        override fun Show(
            onClick: () -> Unit,
            onItemClick: (String) -> Unit
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.categories_screen_title)
                )
                Spacer(modifier = Modifier.height(4.dp))
                content(onClick, onItemClick)
            }
        }
    }

    data object Empty : CategoriesUiState

    data class Error(
        private val errorMessage: String
    ) : Abstract(content = { onClick, _ ->
        CategoryErrorStateContent(errorMessage = errorMessage) {
            onClick.invoke()
        }
    })

    data object Progress : Abstract(content = { _, _ ->
        CategoryProgressStateContent()
    })

    data class Success(
        private val publishedDate: String,
        private val categories: List<CategoryUi>
    ) : Abstract(content = { _, onItemClick: (String) -> Unit ->
        CategorySuccessStateContent(publishedDate = publishedDate, categories = categories) { categoryId ->
            onItemClick.invoke(categoryId)
        }
    })

}