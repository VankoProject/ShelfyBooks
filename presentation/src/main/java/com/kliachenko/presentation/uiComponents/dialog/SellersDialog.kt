package com.kliachenko.presentation.uiComponents.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.model.SellerUi

@Composable
fun SellersDialog(
    sellers: List<SellerUi>,
    onDismiss: () -> Unit,
    onSellerClick: (String, String) -> Unit
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        title = { Text(stringResource(R.string.choose_a_seller)) },
        text = {
            Column {
                sellers.forEach { seller ->
                    seller.Show { name, url ->
                        onSellerClick(name, url)
                        onDismiss()
                    }
                }
            }
        }
    )

}

@Preview(showBackground = true, apiLevel = 34)
@Composable
fun PreviewSellersDialog() {
    SellersDialog(
        sellers = listOf(
            SellerUi.Base(
                name = "Amazon",
                url = "http://fake"
            ),
            SellerUi.Base(
                name = "Apple",
                url = "http://fake"
            ), SellerUi.Base(
                name = "Walmart",
                url = "http://fake"
            )
        ),
        onDismiss = {},
        onSellerClick = { _, _ -> }
    )

}

