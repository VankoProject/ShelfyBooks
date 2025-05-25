package com.kliachenko.shelfybooks.di.data

import com.kliachenko.data.cloud.BookCloudDataSource
import com.kliachenko.data.cloud.CategoryCloudDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindBookCloudDataSource(
        impl: BookCloudDataSource.Base
    ): BookCloudDataSource

    @Binds
    abstract fun bindCategoryCloudDataSource(
        impl: CategoryCloudDataSource.Base
    ): CategoryCloudDataSource

}