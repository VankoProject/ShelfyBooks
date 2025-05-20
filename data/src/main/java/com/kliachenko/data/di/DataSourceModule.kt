package com.kliachenko.data.di

import com.kliachenko.data.cloud.BookCloudDataSource
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
}