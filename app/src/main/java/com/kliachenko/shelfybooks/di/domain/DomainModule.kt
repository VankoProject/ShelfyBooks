package com.kliachenko.shelfybooks.di.domain

import com.kliachenko.domain.usecase.BooksByCategory
import com.kliachenko.domain.usecase.Categories
import com.kliachenko.domain.usecase.SignInWithGoogle
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindBooksByCategoryUseCase(impl: BooksByCategory.Interactor): BooksByCategory

    @Binds
    fun bindCategoriesUseCase(impl: Categories.Interactor): Categories

    @Binds
    fun bindSignInWithGoogle(impl: SignInWithGoogle.Interactor): SignInWithGoogle

}