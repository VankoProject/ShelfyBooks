package com.kliachenko.presentation.books.model

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

interface SellerUi {

    @Composable
    fun Show(onSellerClick: (String, String) -> Unit)

    data class Base(
        private val name: String,
        private val url: String
    ) : SellerUi {

        @Composable
        override fun Show(onSellerClick: (String, String) -> Unit) {
            TextButton(onClick = { onSellerClick(name, url) }) {
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    text = name)
            }
        }
    }

}


