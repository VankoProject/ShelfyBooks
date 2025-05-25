package com.kliachenko.presentation.books

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface BookStoreLinkHandler {

    fun open(url: String)

    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : BookStoreLinkHandler {

        override fun open(url: String) {
            val tabBuilder = CustomTabsIntent.Builder()
                .setToolbarCornerRadiusDp(8)
                .addDefaultShareMenuItem()
                .setShowTitle(true)

            val customTabsIntent = tabBuilder.build()
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }
    }

}