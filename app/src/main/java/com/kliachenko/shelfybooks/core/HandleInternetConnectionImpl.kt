package com.kliachenko.shelfybooks.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.kliachenko.presentation.auth.HandleInternetConnection
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HandleInternetConnectionImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : HandleInternetConnection {

    override fun hasInternet(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

}