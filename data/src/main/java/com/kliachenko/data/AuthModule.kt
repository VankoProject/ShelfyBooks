package com.kliachenko.data

import android.content.Context
import androidx.credentials.CredentialManager
import com.google.firebase.auth.FirebaseAuth
import com.kliachenko.data.authService.Auth
import com.kliachenko.data.authService.HandleAuthException
import com.kliachenko.data.authService.ProvideGoogleCredentialRequest
import com.kliachenko.data.authService.ProvideNonce
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindProvideNonce(nonce: ProvideNonce.Base): ProvideNonce

    @Binds
    abstract fun bindGoogleCredentialRequest(
        request: ProvideGoogleCredentialRequest.Base
    ): ProvideGoogleCredentialRequest

    @Binds
    abstract fun bindHandleAuthException(impl: HandleAuthException.Base): HandleAuthException

    companion object {

        @Provides
        @Singleton
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

        @Provides
        @Singleton
        fun provideCredentialManager(@ApplicationContext context: Context) =
            CredentialManager.create(context)

        @Provides
        @Singleton
        fun provideAuth(
            firebaseAuth: FirebaseAuth,
            handleAuthException: HandleAuthException,
        ): Auth {
            return Auth.CredentialManagerGoogleAuth(firebaseAuth, handleAuthException)
        }
    }

}