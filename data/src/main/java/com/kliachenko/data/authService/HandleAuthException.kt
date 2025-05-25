package com.kliachenko.data.authService

import android.credentials.GetCredentialException
import android.os.Build
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.kliachenko.domain.core.HandleError
import com.kliachenko.data.core.ProvideExceptionMessage
import com.kliachenko.data.core.ProvideResources
import java.net.UnknownHostException
import javax.inject.Inject

interface HandleAuthException: HandleError<String> {

    fun handle(exception: FirebaseAuthException): String

    fun handle(exception: GetCredentialException): String

    fun userNotFound(): String

    class Base @Inject constructor(
        private val firebaseExceptionMessage: ProvideExceptionMessage.FirebaseAuthExceptionMessage,
        private val credentialExceptionMessage: ProvideExceptionMessage.CredentialExceptionMessage,
        private val networkExceptionMessage: ProvideExceptionMessage.NetworkExceptionMessage,
        private val provideResources: ProvideResources,
    ) : HandleAuthException {

        override fun handle(exception: FirebaseAuthException): String {
            return when (exception) {
                is FirebaseAuthInvalidCredentialsException ->
                    firebaseExceptionMessage.invalidCredentials()

                is FirebaseAuthUserCollisionException ->
                    firebaseExceptionMessage.userCollision()

                is FirebaseAuthInvalidUserException ->
                    firebaseExceptionMessage.invalidUser()

                else -> firebaseExceptionMessage.unexpectedFirebaseError()
            }
        }

        override fun handle(exception: GetCredentialException): String {

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                when (exception.type) {

                    GetCredentialException.TYPE_NO_CREDENTIAL ->
                        credentialExceptionMessage.noCredentials()

                    GetCredentialException.TYPE_USER_CANCELED ->
                        credentialExceptionMessage.userCanceled()

                    GetCredentialException.TYPE_INTERRUPTED ->
                        credentialExceptionMessage.interrupted()

                    GetCredentialException.TYPE_UNKNOWN ->
                        credentialExceptionMessage.unknownError()

                    else -> credentialExceptionMessage.unexpectedCredentialError(exception.message)
                }
            } else credentialExceptionMessage.unknownError()
        }

        override fun handle(exception: Exception): String {
            return when (exception) {
                is UnknownHostException -> networkExceptionMessage.noInternetConnection()
                else -> networkExceptionMessage.serviceUnavailable()
            }
        }

        override fun userNotFound() = provideResources.userNotFound()

    }

}