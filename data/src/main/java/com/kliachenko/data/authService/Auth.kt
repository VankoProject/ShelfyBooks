package com.kliachenko.data.authService

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface Auth : UserSession {

    suspend fun authWithGoogle(googleIdToken: String): AuthResult

    class CredentialManagerGoogleAuth @Inject constructor(
        private val auth: FirebaseAuth,
        private val handleAuthException: HandleAuthException,
    ) : Auth {

        override suspend fun authWithGoogle(googleIdToken: String): AuthResult {
            return try {
                val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
                val authResult = auth.signInWithCredential(firebaseCredential).await()
                val currentUser: FirebaseUser? = authResult.user
                return currentUser?.run {
                    AuthResult.Success
                } ?: AuthResult.Error(handleAuthException.userNotFound())
            } catch (e: FirebaseAuthException) {
                AuthResult.Error(handleAuthException.handle(e))
            } catch (e: Exception) {
                AuthResult.Error(handleAuthException.handle(e))
            }
        }

        override fun isLoggedIn() = auth.currentUser != null
    }

}

interface AuthResult {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun mapSuccess(): T
        fun mapFailed(error: String): T
    }

    object Success : AuthResult {
        override fun <T> map(mapper: Mapper<T>) = mapper.mapSuccess()
    }

    data class Error(private val error: String) : AuthResult {
        override fun <T> map(mapper: Mapper<T>) = mapper.mapFailed(error)
    }

}