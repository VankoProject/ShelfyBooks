package com.kliachenko.presentation.auth

import android.app.Activity
import android.content.Context
import androidx.credentials.CredentialManager
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import javax.inject.Inject

interface ProvideGoogleIdToken {

    suspend fun token(activity: Activity): String?

    class Base @Inject constructor(
        private val context: Context,
        private val credentialRequest: GoogleCredentialRequest,
    ) : ProvideGoogleIdToken {

        override suspend fun token(activity: Activity): String? {
            return try {
                val credentialManager = CredentialManager.create(context)
                val request = credentialRequest.request()
                val result = credentialManager.getCredential(
                    request = request, context = activity
                )
                val googleCredential = result.credential as? GoogleIdTokenCredential
                googleCredential?.idToken
            } catch (e: Exception) {
                null
            }
        }
    }

}