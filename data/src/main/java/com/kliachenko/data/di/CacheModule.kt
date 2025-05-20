package com.kliachenko.data.di

import android.content.Context
import androidx.room.Room
import com.kliachenko.data.localCache.AppDataBase
import com.kliachenko.data.localCache.BookCacheDataSource
import com.kliachenko.data.localCache.CategoryCacheDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    @Singleton
    abstract fun bindCategoryCacheDataSource(
        impl: CategoryCacheDataSource.Base
    ): CategoryCacheDataSource.Mutable

    @Binds
    @Singleton
    abstract fun bindBookCacheDataSource(
        impl: BookCacheDataSource.Base
    ): BookCacheDataSource.Mutable

    companion object {

        private const val DATABASE_NAME = "books_db"

        @Provides
        @Singleton
        fun provideDataBase(@ApplicationContext context: Context): AppDataBase =
            Room.databaseBuilder(
                context = context,
                klass = AppDataBase::class.java,
                name = DATABASE_NAME
            ).fallbackToDestructiveMigration(false).build()

        @Provides
        @Singleton
        fun provideCategoryDao(dataBase: AppDataBase) = dataBase.categoryDao()

        @Provides
        @Singleton
        fun provideBookDao(dataBase: AppDataBase) = dataBase.bookDao()

    }

}