package com.kliachenko.shelfybooks.core

import android.content.Context
import com.kliachenko.data.core.ProvideResources
import com.kliachenko.shelfybooks.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ProvideResourcesImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ProvideResources {
    override fun userNotFound() = context.getString(R.string.user_not_found)
}