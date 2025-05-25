package com.kliachenko.domain.usecase

import com.kliachenko.domain.repository.AuthRepository
import com.kliachenko.domain.repository.AuthResult
import javax.inject.Inject

interface SignInWithGoogle {

    suspend operator fun invoke(idToken: String): AuthResult

    class Interactor @Inject constructor(
        private val repository: AuthRepository
    ) : SignInWithGoogle {

        override suspend fun invoke(idToken: String): AuthResult =
            repository.authWithGoogle(idToken)

    }
}