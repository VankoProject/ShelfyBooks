package com.kliachenko.presentation.auth

import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

interface ProvideNonce {

    fun nonce(): String

    class Base @Inject constructor() : ProvideNonce {
        override fun nonce(): String {
            val rawNonce = UUID.randomUUID().toString()
            val md = MessageDigest.getInstance(ALGORITHM)
            val digest = md.digest(rawNonce.toByteArray())
            return digest.joinToString("") { HEX_FORMAT.format(it) }
        }

        private companion object {
            private const val ALGORITHM = "SHA-256"
            private const val HEX_FORMAT = "%02x"
        }
    }

}