package com.kliachenko.data.core

interface ProvideExceptionMessage {

    interface FirebaseAuthExceptionMessage {

        fun invalidCredentials(): String

        fun userCollision(): String

        fun invalidUser(): String

        fun unexpectedFirebaseError(): String

    }

    interface CredentialExceptionMessage {

        fun noCredentials(): String

        fun userCanceled(): String

        fun interrupted(): String

        fun unknownError(): String

        fun unexpectedCredentialError(message: String?): String

    }

    interface NetworkExceptionMessage {

        fun noInternetConnection(): String

        fun serviceUnavailable(): String

    }

}


