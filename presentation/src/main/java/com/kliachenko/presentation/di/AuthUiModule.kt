package com.kliachenko.presentation.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.kliachenko.presentation.auth.ProvideClientId
import com.kliachenko.presentation.auth.ProvideGoogleIdToken
import com.kliachenko.presentation.auth.ProvideNonce
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
abstract class AuthUiModule {

    @Binds
    abstract fun bindGoogleIdTokenProvider(
        impl: ProvideGoogleIdToken.Base
    ): ProvideGoogleIdToken

    @Binds
    abstract fun bindProvideClientId(
        impl: ProvideClientId.Base
    ): ProvideClientId

    @Binds
    abstract fun bindProvideNonce(
        impl: ProvideNonce.Base
    ): ProvideNonce

    companion object {

        @Provides
        @Singleton
        fun provideCredentialManager(@ApplicationContext context: Context) =
            CredentialManager.create(context)

    }

}