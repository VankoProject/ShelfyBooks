package com.kliachenko.shelfybooks.di.core

import com.kliachenko.data.core.ProvideClientId
import com.kliachenko.data.core.ProvideExceptionMessage
import com.kliachenko.data.core.ProvideResources
import com.kliachenko.shelfybooks.core.ProvideClientIdImpl
import com.kliachenko.shelfybooks.core.ProvideExceptionMessageImpl
import com.kliachenko.shelfybooks.core.ProvideResourcesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreBindModule {

    @Binds
    @Singleton
    abstract fun bindProvideClientId(impl: ProvideClientIdImpl): ProvideClientId

    @Binds
    @Singleton
    abstract fun bindProvideResources(impl: ProvideResourcesImpl): ProvideResources

    @Binds
    @Singleton
    abstract fun bindFirebaseExceptionMessage(
        impl: ProvideExceptionMessageImpl
    ): ProvideExceptionMessage.FirebaseAuthExceptionMessage

    @Binds
    @Singleton
    abstract fun bindCredentialExceptionMessage(
        impl: ProvideExceptionMessageImpl
    ): ProvideExceptionMessage.CredentialExceptionMessage

    @Binds
    @Singleton
    abstract fun bindNetworkExceptionMessage(
        impl: ProvideExceptionMessageImpl
    ): ProvideExceptionMessage.NetworkExceptionMessage

}