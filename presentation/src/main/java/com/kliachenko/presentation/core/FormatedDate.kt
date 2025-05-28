package com.kliachenko.presentation.core

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

interface FormatedDate {

    fun dateFormat(date: String): String

    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : FormatedDate {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun dateFormat(date: String): String {
            return try {
                val parsedDate = LocalDate.parse(date)
                val appLocale = context.resources.configuration.locales[0]
                val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", appLocale)
                parsedDate.format(formatter)
            } catch (e: Exception) {
                date
            }
        }
    }

}