package com.kliachenko.shelfybooks.core

import android.content.Context
import com.kliachenko.data.core.ProvideExceptionMessage
import com.kliachenko.shelfybooks.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ProvideExceptionMessageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ProvideExceptionMessage.FirebaseAuthExceptionMessage,
    ProvideExceptionMessage.CredentialExceptionMessage,
    ProvideExceptionMessage.NetworkExceptionMessage {

    override fun invalidCredentials() = context.getString(R.string.error_invalid_credentials)

    override fun userCollision() = context.getString(R.string.error_user_collision)

    override fun invalidUser() = context.getString(R.string.error_invalid_user)

    override fun unexpectedFirebaseError() = context.getString(R.string.error_unexpected_firebase)

    override fun noCredentials() = context.getString(R.string.error_no_credentials)

    override fun userCanceled() = context.getString(R.string.error_user_canceled)

    override fun interrupted() = context.getString(R.string.error_interrupted)

    override fun unknownError() = context.getString(R.string.error_unknown)

    override fun unexpectedCredentialError(message: String?) =
        context.getString(
            R.string.error_unexpected_credential, message ?: R.string.error_unknown
        )

    override fun noInternetConnection() = context.getString(R.string.error_no_internet)

    override fun serviceUnavailable() = context.getString(R.string.error_service_unavailable)

}