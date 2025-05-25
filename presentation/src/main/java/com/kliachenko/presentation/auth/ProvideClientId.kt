package com.kliachenko.presentation.auth

import android.content.Context
import com.kliachenko.presentation.R
import javax.inject.Inject

interface ProvideClientId {

    fun clientId(): String

    class Base @Inject constructor(
        private val context: Context
    ) : ProvideClientId {

        override fun clientId() = context.getString(R.string.default_web_client_id)
    }

}