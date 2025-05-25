package com.kliachenko.shelfybooks.di.presentation

import com.kliachenko.presentation.books.BookStoreLinkHandler
import com.kliachenko.presentation.core.RunAsync
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UiModule {

    @Binds
    abstract fun bindRunAsync(
        impl: RunAsync.Base
    ): RunAsync

    @Binds
    abstract fun bindBookStoreLinkHandler(
        impl: BookStoreLinkHandler.Base
    ): BookStoreLinkHandler

}