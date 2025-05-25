package com.kliachenko.presentation.books

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.model.SellerUi

@Composable
fun SellerButton(
    sellers: List<SellerUi>,
    state: SellersButtonUiState,
    onButtonClick: (List<SellerUi>) -> Unit
) {
    state.Show(
        sellers = sellers,
        onButtonClick = onButtonClick,
    )
}

interface SellersButtonUiState {

    @Composable
    fun Show(
        sellers: List<SellerUi>,
        onButtonClick: (List<SellerUi>) -> Unit
    )

    abstract class Abstract(
        private val content: @Composable (RowScope.() -> Unit),
    ) : SellersButtonUiState {

        @Composable
        override fun Show(
            sellers: List<SellerUi>,
            onButtonClick: (List<SellerUi>) -> Unit
        ) {
            Button(
                modifier = Modifier.height(20.dp),
                contentPadding = PaddingValues(0.dp),
                onClick = { onButtonClick.invoke(sellers) },
                content = content
            )
        }
    }


    data object BuyAction : Abstract(content = {
        Text(
            text = stringResource(id = R.string.book_buy_button),
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    })

    data object Progress : Abstract(content = {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = MaterialTheme.colorScheme.onPrimary,
            strokeWidth = 2.dp
        )
    })

}