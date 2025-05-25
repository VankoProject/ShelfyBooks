package com.kliachenko.presentation.books.model

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.kliachenko.presentation.books.BookStoreLinkHandler

interface SellerUi {

    @Composable
    fun Show(onSellerClick: () -> Unit)

    fun buy(opener: BookStoreLinkHandler)

    data class Base(
        private val name: String,
        private val url: String
    ) : SellerUi {

        @Composable
        override fun Show(onSellerClick: () -> Unit) {
            TextButton(onClick = onSellerClick) {
                Text(text = name)
            }
        }

        override fun buy(opener: BookStoreLinkHandler) {
            opener.open(url)
        }

    }

}


