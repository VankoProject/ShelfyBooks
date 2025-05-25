package com.kliachenko.data.di

import com.google.firebase.auth.FirebaseAuth
import com.kliachenko.data.authService.Auth
import com.kliachenko.data.authService.HandleAuthException
import com.kliachenko.domain.core.HandleError
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindHandleAuthException(impl: HandleAuthException.Base): HandleAuthException

    @Binds
    @Singleton
    abstract fun bindHandleError(impl: HandleAuthException.Base): HandleError<String>

    companion object {

        @Provides
        @Singleton
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

        @Provides
        @Singleton
        fun provideAuth(
            firebaseAuth: FirebaseAuth,
        ): Auth {
            return Auth.CredentialManagerGoogleAuth(firebaseAuth)
        }
    }

}