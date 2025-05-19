package com.kliachenko.shelfybooks.core

import android.content.Context
import com.kliachenko.data.core.ProvideClientId
import com.kliachenko.shelfybooks.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProvideClientIdImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ProvideClientId {
    override fun firebaseClientId() = context.getString(R.string.default_web_client_id)
}