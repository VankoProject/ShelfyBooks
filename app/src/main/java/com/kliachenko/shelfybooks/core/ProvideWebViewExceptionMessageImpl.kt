package com.kliachenko.shelfybooks.core

import android.content.Context
import android.webkit.WebViewClient
import com.kliachenko.presentation.core.HandleWebViewException
import com.kliachenko.shelfybooks.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ProvideWebViewExceptionMessageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) :
    HandleWebViewException {
    override fun handle(errorCode: Int?, description: CharSequence?): String {
        return when (errorCode) {
            WebViewClient.ERROR_TIMEOUT -> context.getString(R.string.error_timeout)
            WebViewClient.ERROR_CONNECT -> context.getString(R.string.error_no_connection)
            WebViewClient.ERROR_HOST_LOOKUP -> context.getString(R.string.error_host_lookup)
            WebViewClient.ERROR_UNSUPPORTED_AUTH_SCHEME -> context.getString(R.string.error_unsupported_auth)
            WebViewClient.ERROR_AUTHENTICATION -> context.getString(R.string.error_authentication)
            WebViewClient.ERROR_PROXY_AUTHENTICATION -> context.getString(R.string.error_proxy_authentication)
            WebViewClient.ERROR_TOO_MANY_REQUESTS -> context.getString(R.string.error_too_many_requests)
            else -> description?.toString() ?: context.getString(R.string.unknown_webview_error)
        }
    }
}