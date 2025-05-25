package com.kliachenko.data.authService

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface Auth : UserSession {

    suspend fun authWithGoogle(idToken: String): FirebaseUser?

    class CredentialManagerGoogleAuth @Inject constructor(
        private val auth: FirebaseAuth
    ) : Auth {

        override suspend fun authWithGoogle(idToken: String): FirebaseUser? {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            return result.user
        }

        override fun isLoggedIn(): Boolean = auth.currentUser != null

    }

}