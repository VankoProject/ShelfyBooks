package com.kliachenko.shelfybooks.di

import com.kliachenko.domain.usecase.BooksByCategory
import com.kliachenko.domain.usecase.BooksInteractor
import com.kliachenko.domain.usecase.Categories
import com.kliachenko.domain.usecase.CategoriesInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    abstract fun bindBooksByCategoryUseCase(impl: BooksInteractor): BooksByCategory

    @Binds
    abstract fun bindCategoriesUseCase(impl: CategoriesInteractor): Categories

}