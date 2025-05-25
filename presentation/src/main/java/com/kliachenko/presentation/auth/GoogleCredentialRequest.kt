package com.kliachenko.presentation.auth

import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import javax.inject.Inject

interface GoogleCredentialRequest {

    fun request(): GetCredentialRequest

    class Base @Inject constructor(
        private val clientId: ProvideClientId,
        private val nonce: ProvideNonce
    ) : GoogleCredentialRequest {

        override fun request(): GetCredentialRequest {
            val googleOptions = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(clientId.clientId())
                .setNonce(nonce.nonce())
                .setAutoSelectEnabled(false)
                .build()

            return GetCredentialRequest.Builder()
                .addCredentialOption(googleOptions)
                .build()
        }
    }

}