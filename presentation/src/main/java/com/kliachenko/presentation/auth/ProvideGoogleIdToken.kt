package com.kliachenko.presentation.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import java.net.UnknownHostException
import javax.inject.Inject

interface ProvideGoogleIdToken {

    suspend fun token(activity: Activity): TokenResult

    class Base @Inject constructor(
        private val context: Context,
        private val credentialRequest: GoogleCredentialRequest,
        private val handleInternet: HandleInternetConnection,
    ) : ProvideGoogleIdToken {

        override suspend fun token(activity: Activity): TokenResult {
            return try {
                val credentialManager = CredentialManager.create(context)
                val request = credentialRequest.request()
                val result = credentialManager.getCredential(
                    request = request,
                    context = activity
                )
                val credential = result.credential
                when (credential) {
                    is CustomCredential -> {
                        if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                            val google = GoogleIdTokenCredential.createFrom(credential.data)
                            val idToken = google.idToken
                            TokenResult.Success(idToken)
                        } else {
                            TokenResult.Error(IllegalStateException("Unsupported CustomCredential type: ${credential.type}"))
                        }
                    }
                    else -> TokenResult.Error(IllegalStateException("Unsupported credential: ${credential::class.java.name}"))
                }

            } catch (e: androidx.credentials.exceptions.GetCredentialException) {
                val hasInternet = handleInternet.hasInternet()
                return if (!hasInternet) {
                    TokenResult.Error(UnknownHostException())
                } else {
                    TokenResult.Canceled
                }
            } catch (e: Exception) {
                TokenResult.Error(e)
            }
        }
    }

}

interface TokenResult {

    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapSuccess(data: String): T
        fun mapError(exception: Exception): T
        fun mapCanceled(): T
    }

    data class Success(private val token: String) : TokenResult {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.mapSuccess(token)
    }

    data class Error(val exception: Exception) : TokenResult {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.mapError(exception)
    }

    data object Canceled : TokenResult {
        override fun <T : Any> map(mapper: Mapper<T>) = mapper.mapCanceled()
    }

}