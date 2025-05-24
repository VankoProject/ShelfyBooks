package com.kliachenko.presentation.books.model

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kliachenko.presentation.R

interface BookRateUiState {

    @Composable
    fun Show()

    data class Base(
        private val bookRate: Int
    ) : BookRateUiState {
        @Composable
        override fun Show() {
            when (bookRate) {
                1 -> IconRate(
                    painter = painterResource(id = R.drawable.first_place_icon),
                    placeNumber = stringResource(R.string.first_place)
                )

                2 -> IconRate(
                    painter = painterResource(id = R.drawable.second_place_icon),
                    placeNumber = stringResource(R.string.second_place)
                )

                3 -> IconRate(
                    painter = painterResource(id = R.drawable.third_place_icon),
                    placeNumber = stringResource(R.string.third_place)
                )

                else -> Text(
                    text = bookRate.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}

@Composable
fun IconRate(painter: Painter, placeNumber: String) {
    Icon(
        painter = painter,
        contentDescription = placeNumber,
        modifier = Modifier.size(24.dp)
    )
}