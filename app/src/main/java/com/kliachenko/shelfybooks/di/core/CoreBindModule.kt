package com.kliachenko.shelfybooks.di.core

import android.content.Context
import com.kliachenko.data.core.ProvideExceptionMessage
import com.kliachenko.data.core.ProvideResources
import com.kliachenko.presentation.auth.HandleInternetConnection
import com.kliachenko.presentation.core.FormatedDate
import com.kliachenko.presentation.core.HandleWebViewException
import com.kliachenko.shelfybooks.core.HandleInternetConnectionImpl
import com.kliachenko.shelfybooks.core.ProvideExceptionMessageImpl
import com.kliachenko.shelfybooks.core.ProvideResourcesImpl
import com.kliachenko.shelfybooks.core.ProvideWebViewExceptionMessageImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreBindModule {

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

    @Binds
    @Singleton
    abstract fun bindHandleInternetConnection(
        impl: HandleInternetConnectionImpl
    ): HandleInternetConnection

    @Binds
    @Singleton
    abstract fun bindWebViewExceptionMessage(
        impl: ProvideWebViewExceptionMessageImpl
    ): HandleWebViewException

    @Binds
    @Singleton
    abstract fun bindFormatedDate(
        impl: FormatedDate.Base
    ): FormatedDate


    companion object {

        @Provides
        fun provideContext(@ApplicationContext context: Context): Context = context

    }

}