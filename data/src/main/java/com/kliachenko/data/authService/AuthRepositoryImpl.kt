package com.kliachenko.data.authService

import com.google.firebase.auth.FirebaseAuthException
import com.kliachenko.domain.repository.AuthRepository
import com.kliachenko.domain.repository.AuthResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: Auth,
    private val handleAuthException: HandleAuthException,
) : AuthRepository {

    override suspend fun authWithGoogle(idToken: String): AuthResult {
        return try {
            val user = auth.authWithGoogle(idToken)
            if (user != null) AuthResult.Success
            else AuthResult.Error(handleAuthException.userNotFound())
        } catch (e: FirebaseAuthException) {
            AuthResult.Error(handleAuthException.handle(e))
        } catch (e: Exception) {
            AuthResult.Error(handleAuthException.handle(e))
        }
    }


    override fun isLoggedIn(): Boolean = auth.isLoggedIn()

}

