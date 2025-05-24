package com.kliachenko.presentation.books.model

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

interface SellerUi {

    @Composable
    fun Show(onSellerClick: () -> Unit)

    fun open(context: Context)

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

        override fun open(context: Context) {
            val tabBuilder = CustomTabsIntent.Builder()
                .setToolbarCornerRadiusDp(8)
                .addDefaultShareMenuItem()
                .setShowTitle(true)

            val customTabsIntent = tabBuilder.build()
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }

    }
}


