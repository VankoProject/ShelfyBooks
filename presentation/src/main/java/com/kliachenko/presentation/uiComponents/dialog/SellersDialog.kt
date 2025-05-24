package com.kliachenko.presentation.uiComponents.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kliachenko.presentation.R
import com.kliachenko.presentation.books.model.SellerUi

@Composable
fun SellersDialog(
    sellers: List<SellerUi>,
    onDismiss: () -> Unit,
    onSellerClick: (SellerUi) -> Unit
) {
    AlertDialog(
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
                    seller.Show {
                        onSellerClick.invoke(seller)
                    }
                }
            }
        }
    )
}

