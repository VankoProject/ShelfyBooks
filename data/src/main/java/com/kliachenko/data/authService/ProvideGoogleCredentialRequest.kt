package com.kliachenko.data.authService

import androidx.credentials.GetCredentialRequest
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.kliachenko.data.core.ProvideClientId
import javax.inject.Inject

interface ProvideGoogleCredentialRequest {

    fun request(): GetCredentialRequest

    class Base @Inject constructor(
        private val clientId: ProvideClientId,
        private val nonce: ProvideNonce,
    ) : ProvideGoogleCredentialRequest {
        override fun request(): GetCredentialRequest {
            val googleOptions: GetGoogleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(clientId.firebaseClientId())
                .setNonce(nonce.provideNonce())
                .setAutoSelectEnabled(false)
                .build()

            return GetCredentialRequest.Builder()
                .addCredentialOption(googleOptions)
                .build()
        }
    }

}