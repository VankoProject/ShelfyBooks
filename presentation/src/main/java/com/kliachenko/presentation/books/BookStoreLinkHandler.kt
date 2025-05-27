package com.kliachenko.presentation.books

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import javax.inject.Inject

interface BookStoreLinkHandler {

    fun open(activity: Activity, url: String)

    class Base @Inject constructor() : BookStoreLinkHandler {

        override fun open(activity: Activity, url: String) {
            val uri = Uri.parse(url)

            val customTabsIntent = CustomTabsIntent.Builder()
                .setShowTitle(true)
                .addDefaultShareMenuItem()
                .setToolbarCornerRadiusDp(8)
                .build()

            customTabsIntent.intent.setPackage("com.android.chrome")

            try {
                customTabsIntent.launchUrl(activity, uri)
            } catch (e: ActivityNotFoundException) {
                val fallbackIntent = Intent(Intent.ACTION_VIEW, uri)
                activity.startActivity(fallbackIntent)
            }
        }
    }

}