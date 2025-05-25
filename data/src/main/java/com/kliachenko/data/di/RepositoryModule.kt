package com.kliachenko.data.di

import com.kliachenko.data.BookRepositoryImpl
import com.kliachenko.data.CategoryRepositoryImpl
import com.kliachenko.data.authService.AuthRepositoryImpl
import com.kliachenko.domain.repository.AuthRepository
import com.kliachenko.domain.repository.BookRepository
import com.kliachenko.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindBookRepository(impl: BookRepositoryImpl): BookRepository

    @Binds
    fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

}

